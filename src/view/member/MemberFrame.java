package view.member;

import dao.JenisMemberDao;
import dao.MemberDao;
import java.util.List;
import javax.swing.*;
import model.JenisMember;
import model.Member;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private final List<Member> memberList;
    private final JTextField textFieldNama;
    private final MemberTableModel tableModel;
    private final JComboBox<String> comboJenis;
    private final MemberDao memberDao;
    private final JenisMemberDao jenisMemberDao;
    private final JTable table;

    @SuppressWarnings("unused")
    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 150, 30);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 160, 100, 40);

        JButton buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(120, 160, 100, 40);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(230, 160, 100, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        MemberButtonSimpanActionListener actionListenerSimpan = new MemberButtonSimpanActionListener(this, memberDao);
        buttonSimpan.addActionListener(actionListenerSimpan);

        // Action listeners for update and delete
        buttonUpdate.addActionListener(e -> updateMember());
        buttonDelete.addActionListener(e -> deleteMember());

        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Update member
    public void updateMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString(); // Get ID
            String newNama = JOptionPane.showInputDialog(this, "Masukkan nama baru:");

            if (newNama != null && !newNama.trim().isEmpty()) {
                Member member = memberDao.findById(id);
                member.setNama(newNama);
                member.setJenisMember(getJenisMember());

                if (memberDao.update(member) > 0) {
                    JOptionPane.showMessageDialog(this, "Member berhasil diperbarui.");
                    tableModel.update(selectedRow, member); // Update table row
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal memperbarui member.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih member yang ingin diperbarui.");
        }
    }

    // Delete member
    public void deleteMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString(); // Get ID
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus member ini?");
            if (confirm == JOptionPane.YES_OPTION) {
                Member member = memberDao.findById(id);
                if (memberDao.delete(member) > 0) {
                    JOptionPane.showMessageDialog(this, "Member berhasil dihapus.");
                    tableModel.remove(selectedRow); // Remove row from table
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus member.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih member yang ingin dihapus.");
        }
    }
}
