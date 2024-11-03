import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingComponentsExample extends JFrame {
    public SwingComponentsExample() {
        setTitle("Contoh Implementasi Komponen Swing");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 1. JTextField
        JTextField textField = new JTextField(20);
        add(new JLabel("Input Text:"));
        add(textField);

        // 2. JTextArea
        JTextArea textArea = new JTextArea(5, 20);
        add(new JLabel("Area Text:"));
        add(new JScrollPane(textArea));

        // 3. JRadioButton
        JRadioButton option1 = new JRadioButton("Option 1");
        JRadioButton option2 = new JRadioButton("Option 2");
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(option1);
        radioGroup.add(option2);
        add(new JLabel("Pilihan Radio:"));
        add(option1);
        add(option2);

        // 4. JCheckBox
        JCheckBox checkBox1 = new JCheckBox("CheckBox 1");
        JCheckBox checkBox2 = new JCheckBox("CheckBox 2");
        add(new JLabel("Pilihan Checkbox:"));
        add(checkBox1);
        add(checkBox2);

        // 5. JComboBox
        String[] items = {"Item 1", "Item 2", "Item 3"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        add(new JLabel("Pilihan ComboBox:"));
        add(comboBox);

        // 6. JList
        JList<String> list = new JList<>(items);
        add(new JLabel("Pilihan List:"));
        add(new JScrollPane(list));

        // 7. JSlider
        JSlider slider = new JSlider(0, 100, 50);
        add(new JLabel("Slider:"));
        add(slider);

        // 8. JSpinner
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        add(new JLabel("Spinner:"));
        add(spinner);

        // 9. JMenu dan JMenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem1 = new JMenuItem("Item 1");
        JMenuItem menuItem2 = new JMenuItem("Item 2");
        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // 10. JTable
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        Object[][] data = {
            {"Data 1", "Data 2", "Data 3"},
            {"Data 4", "Data 5", "Data 6"},
            {"Data 7", "Data 8", "Data 9"},
        };
        JTable table = new JTable(data, columnNames);
        add(new JLabel("Table:"));
        add(new JScrollPane(table));

        // Tombol untuk menampilkan output
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Contoh pengambilan nilai dari JTextField
                String text = textField.getText();
                System.out.println("Input Text: " + text);

                // Contoh pengambilan nilai dari JTextArea
                String areaText = textArea.getText();
                System.out.println("Area Text: " + areaText);

                // Contoh pengambilan nilai dari JRadioButton
                System.out.println("Radio Selected: " + (option1.isSelected() ? "Option 1" : "Option 2"));

                // Contoh pengambilan nilai dari JCheckBox
                System.out.println("Checkbox 1: " + checkBox1.isSelected());
                System.out.println("Checkbox 2: " + checkBox2.isSelected());

                // Contoh pengambilan nilai dari JComboBox
                System.out.println("ComboBox Selected: " + comboBox.getSelectedItem());

                // Contoh pengambilan nilai dari JList
                System.out.println("List Selected: " + list.getSelectedValue());

                // Contoh pengambilan nilai dari JSlider
                System.out.println("Slider Value: " + slider.getValue());

                // Contoh pengambilan nilai dari JSpinner
                System.out.println("Spinner Value: " + spinner.getValue());
            }
        });
        add(submitButton);
    }

    public static void main(String[] args) {
        SwingComponentsExample example = new SwingComponentsExample();
        example.setVisible(true);
    }
}
