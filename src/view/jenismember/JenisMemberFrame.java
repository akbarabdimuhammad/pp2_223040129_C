package view.jenismember;

import dao.JenisMemberDao;
import java.util.List;
import javax.swing.*;
import model.JenisMember;

public class JenisMemberFrame extends JFrame {
    private final List<JenisMember> jenisMemberList;
    private final JTextField textFieldNama;
    private final JenisMemberTableModel tableModel;
    private final JenisMemberDao jenisMemberDao;
    private final JTable table;  // Marked as final

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();  

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Jenis Member");

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 100, 100, 40);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(230, 100, 100, 40);

        table = new JTable();  // Initialize table as final
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Action listener for the "Simpan" button
        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);

        // Action listener for the "Update" button
        buttonUpdate.addActionListener(_ -> updateJenisMember());

        // Action listener for the "Delete" button
        buttonDelete.addActionListener(_ -> deleteJenisMember());

        this.add(button);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    // Menambahkan jenis member ke dalam tabel dan menyimpannya ke database
    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);  // Menambahkan data ke tabel
        jenisMemberDao.insert(jenisMember);  // Menyimpan data ke database
        textFieldNama.setText("");  // Reset text field setelah data ditambahkan
    }

    // Fungsi untuk Update jenis member yang dipilih
    public void updateJenisMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString(); // Ambil ID yang dipilih
            String newNama = JOptionPane.showInputDialog(this, "Masukkan nama baru:");

            if (newNama != null && !newNama.trim().isEmpty()) {
                JenisMember jenisMember = jenisMemberDao.findById(id);
                jenisMember.setNama(newNama);

                if (jenisMemberDao.update(jenisMember) > 0) {
                    JOptionPane.showMessageDialog(this, "Jenis member berhasil diperbarui.");
                    tableModel.update(selectedRow, jenisMember); // Update data di tabel
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal memperbarui jenis member.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih jenis member yang ingin diperbarui.");
        }
    }

    // Fungsi untuk menghapus jenis member yang dipilih
    public void deleteJenisMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString(); // Ambil ID yang dipilih

            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus jenis member ini?");
            if (confirm == JOptionPane.YES_OPTION) {
                JenisMember jenisMember = jenisMemberDao.findById(id);

                if (jenisMemberDao.delete(jenisMember) > 0) {
                    JOptionPane.showMessageDialog(this, "Jenis member berhasil dihapus.");
                    tableModel.remove(selectedRow); // Hapus data dari tabel
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus jenis member.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih jenis member yang ingin dihapus.");
        }
    }
}
