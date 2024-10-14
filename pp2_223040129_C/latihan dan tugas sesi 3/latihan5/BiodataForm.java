package latihan5;
import java.awt.*;
import javax.swing.*;

public class BiodataForm extends JFrame {

    public BiodataForm() {
       
        setTitle("Form Biodata");

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        setLayout(new BorderLayout());

        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2)); // 4 baris, 2 kolom

        
        formPanel.add(new JLabel("Nama:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Jenis Kelamin:"));

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout());
        JRadioButton maleRadio = new JRadioButton("Laki-Laki");
        JRadioButton femaleRadio = new JRadioButton("Perempuan");
        JRadioButton foreignRadio = new JRadioButton("Warga Negara Asing");

        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(foreignRadio);

        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(foreignRadio);

        formPanel.add(genderPanel);

        formPanel.add(new JLabel("Nomor HP:"));
        JTextField phoneField = new JTextField();
        formPanel.add(phoneField);

      
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String gender = maleRadio.isSelected() ? "Laki-Laki" :
                            femaleRadio.isSelected() ? "Perempuan" : 
                            foreignRadio.isSelected() ? "Warga Negara Asing" : "Tidak Ditentukan"; 
            String phone = phoneField.getText();

            
            JOptionPane.showMessageDialog(BiodataForm.this, "Nama: " + name + "\nJenis Kelamin: " + gender + "\nNomor HP: " + phone);
        });

       
        add(formPanel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);

       
        setSize(300, 200);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BiodataForm());
    }
}
