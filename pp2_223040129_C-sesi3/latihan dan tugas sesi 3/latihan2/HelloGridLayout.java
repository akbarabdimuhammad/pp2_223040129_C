package latihan2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloGridLayout extends JFrame implements ActionListener {
    private final JButton buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH, buttonI;
    private final JButton[] buttons;
    private boolean gameOver;

    public HelloGridLayout() {
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOver = false;

     
        buttonA = new JButton("");
        buttonB = new JButton("");
        buttonC = new JButton("");
        buttonD = new JButton("");
        buttonE = new JButton("");
        buttonF = new JButton("");
        buttonG = new JButton("");
        buttonH = new JButton("");
        buttonI = new JButton("");

      
        buttons = new JButton[]{buttonA, buttonB, buttonC, buttonD, buttonE, buttonF, buttonG, buttonH, buttonI};

       
        this.setLayout(new GridLayout(3, 3));
        for (JButton button : buttons) {
            this.add(button);
        }

     
        for (JButton button : buttons) {
            button.addActionListener(this);
        }

       
        this.setSize(300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        JButton button = (JButton) e.getSource();
        if (button.getText().isEmpty()) {
            button.setText("O");
            checkWinner();
            if (!gameOver) {
                for (JButton b : buttons) {
                    if (b.getText().isEmpty()) {
                        b.setText("X");
                        break;
                    }
                }
                checkWinner();
            }
        }
    }

    private void checkWinner() {
        String winner = "";
        if (!buttonA.getText().isEmpty() &&
            buttonA.getText().equals(buttonB.getText()) &&
            buttonA.getText().equals(buttonC.getText())) {
            winner = buttonA.getText();
            buttonA.setForeground(Color.RED);
            buttonB.setForeground(Color.RED);
            buttonC.setForeground(Color.RED);
        } else if (!buttonD.getText().isEmpty() &&
                   buttonD.getText().equals(buttonE.getText()) &&
                   buttonD.getText().equals(buttonF.getText())) {
            winner = buttonD.getText();
            buttonD.setForeground(Color.RED);
            buttonE.setForeground(Color.RED);
            buttonF.setForeground(Color.RED);
        } else if (!buttonG.getText().isEmpty() &&
                   buttonG.getText().equals(buttonH.getText()) &&
                   buttonG.getText().equals(buttonI.getText())) {
            winner = buttonG.getText();
            buttonG.setForeground(Color.RED);
            buttonH.setForeground(Color.RED);
            buttonI.setForeground(Color.RED);
        } else if (!buttonA.getText().isEmpty() &&
                   buttonA.getText().equals(buttonD.getText()) &&
                   buttonA.getText().equals(buttonG.getText())) {
            winner = buttonA.getText();
            buttonA.setForeground(Color.RED);
            buttonD.setForeground(Color.RED);
            buttonG.setForeground(Color.RED);
        } else if (!buttonB.getText().isEmpty() &&
                   buttonB.getText().equals(buttonE.getText()) &&
                   buttonB.getText().equals(buttonH.getText())) {
            winner = buttonB.getText();
            buttonB.setForeground(Color.RED);
            buttonE.setForeground(Color.RED);
            buttonH.setForeground(Color.RED);
        } else if (!buttonC.getText().isEmpty() &&
                   buttonC.getText().equals(buttonF.getText()) &&
                   buttonC.getText().equals(buttonI.getText())) {
            winner = buttonC.getText();
            buttonC.setForeground(Color.RED);
            buttonF.setForeground(Color.RED);
            buttonI.setForeground(Color.RED);
        } else if (!buttonA.getText().isEmpty() &&
                   buttonA.getText().equals(buttonE.getText()) &&
                   buttonA.getText().equals(buttonI.getText())) {
            winner = buttonA.getText();
            buttonA.setForeground(Color.RED);
            buttonE.setForeground(Color.RED);
            buttonI.setForeground(Color.RED);
        } else if (!buttonC.getText().isEmpty() &&
                   buttonC.getText().equals(buttonE.getText()) &&
                   buttonC.getText().equals(buttonG.getText())) {
            winner = buttonC.getText();
            buttonC.setForeground(Color.RED);
            buttonE.setForeground(Color.RED);
            buttonG.setForeground(Color.RED);
        }

        gameOver = !winner.isEmpty();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            HelloGridLayout h = new HelloGridLayout();
            h.setVisible(true);
        });
    }
}
