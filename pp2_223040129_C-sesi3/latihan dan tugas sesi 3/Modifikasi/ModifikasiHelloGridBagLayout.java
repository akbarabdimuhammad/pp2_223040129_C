package Modifikasi;

import java.awt.*;
import javax.swing.*;

public class ModifikasiHelloGridBagLayout extends JFrame {

    public ModifikasiHelloGridBagLayout() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel headerLabel = new JLabel("Form Pendaftaran", JLabel.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.setSize(300, 300);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Row 1
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nama :"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel("Jenis Kelamin :"), gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JTextField(""), gbc);
        gbc.gridx = 1;
        panel.add(new JRadioButton("Laki Laki"), gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("No Telpon :"), gbc);
        gbc.gridx = 1;
        panel.add(new JRadioButton("Perempuan"), gbc);

        // Row 4
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JTextField(), gbc);
        gbc.gridx = 1;
        panel.add(new JCheckBox("Warga negara Asing"), gbc);

        // Row 5
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Full width for Button 5
        panel.add(new JButton("simpan"), gbc);

        controlPanel.add(panel);

        this.setLayout(new GridLayout(2, 1));
        this.add(headerLabel);
        this.add(controlPanel);
        this.setSize(400, 400);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ModifikasiHelloGridBagLayout h = new ModifikasiHelloGridBagLayout();
            h.setVisible(true);
        });
    }
}
