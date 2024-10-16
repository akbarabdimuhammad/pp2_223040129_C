import java.awt.*;
import javax.swing.*;

public class FormBiodata extends JFrame {
    private final JTextField namaField; // Menambahkan final
    private final JTextField nomorHPField; // Menambahkan final
    private final JRadioButton lakiLakiRadio; // Menambahkan final
    private final JRadioButton perempuanRadio; // Menambahkan final
    private final JCheckBox wnaCheckBox; // Menambahkan final
    private final JButton simpanButton; // Menambahkan final

    public FormBiodata() {
        setTitle("Form Biodata");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Nama
        add(new JLabel("Nama:"));
        namaField = new JTextField();
        add(namaField);

        // Nomor HP
        add(new JLabel("Nomor HP:"));
        nomorHPField = new JTextField();
        add(nomorHPField);

        // Jenis Kelamin
        add(new JLabel("Jenis Kelamin:"));
        JPanel jenisKelaminPanel = new JPanel();
        jenisKelaminPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        lakiLakiRadio = new JRadioButton("Laki-Laki", true);
        perempuanRadio = new JRadioButton("Perempuan");
        ButtonGroup jenisKelaminGroup = new ButtonGroup();
        jenisKelaminGroup.add(lakiLakiRadio);
        jenisKelaminGroup.add(perempuanRadio);
        jenisKelaminPanel.add(lakiLakiRadio);
        jenisKelaminPanel.add(perempuanRadio);
        add(jenisKelaminPanel);

        // Warga Negara Asing
        add(new JLabel(""));
        wnaCheckBox = new JCheckBox("Warga Negara Asing");
        add(wnaCheckBox);

        // Tombol Simpan
        add(new JLabel(""));
        simpanButton = new JButton("Simpan");
        // Menggunakan lambda expression untuk ActionListener
        simpanButton.addActionListener(e -> simpanData());
        add(simpanButton);

        setVisible(true);
    }

    private void simpanData() {
        String nama = namaField.getText();
        String nomorHP = nomorHPField.getText();
        String jenisKelamin = lakiLakiRadio.isSelected() ? "Laki-Laki" : "Perempuan";
        boolean isWNA = wnaCheckBox.isSelected();

        // Menggunakan text block untuk pesan
        String pesan = """
                Data tersimpan:
                Nama: %s
                Nomor HP: %s
                Jenis Kelamin: %s
                Warga Negara Asing: %s
                """.formatted(nama, nomorHP, jenisKelamin, (isWNA ? "Ya" : "Tidak"));

        JOptionPane.showMessageDialog(this, pesan, "Data Tersimpan", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormBiodata()); // Menggunakan lambda expression
    }
}
