package chessgui;

import actions.WinActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinGUI extends JFrame {

    JLabel label = new JLabel("You are WINNER!");
    JPanel jPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setFont(new Font("default", Font.BOLD, 20));


            // Draw rectangle
            g.drawRect(0, 0, 150, 100);

            // Draw text inside the rectangle
            g.drawString("Win!!!", 50-10, 50-10);
        }
    };

    public WinGUI() {

        this.add(label);
        this.add(jPanel);
//        this.setModal(true);

        this.setMinimumSize(new Dimension(150, 100));
        this.pack();
        this.setVisible(true);
    }
}
