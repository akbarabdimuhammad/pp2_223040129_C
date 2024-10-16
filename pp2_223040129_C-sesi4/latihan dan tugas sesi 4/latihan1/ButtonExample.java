package latihan1;

import javax.swing.*; 

public class ButtonExample {
    public static void main(String[] args) {
     
        JFrame frame = new JFrame("Button Example");
        
        JButton button = new JButton("Click Me");
        
        button.addActionListener(e -> {
            System.out.println("Button clicked!");
        });
        
        button.setBounds(50, 50, 150, 30);
        
        frame.add(button);
        frame.setSize(300, 200);
        frame.setLayout(null); 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
