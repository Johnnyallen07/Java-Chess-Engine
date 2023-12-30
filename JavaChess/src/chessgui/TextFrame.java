package chessgui;

import utils.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFrame extends JDialog {
    JLabel label = new JLabel("Text Frame");
    JPanel jPanel = new JPanel(/*new BorderLayout()*/);

    JTextField wText = new JTextField(16);
    JTextField bText = new JTextField(16);

    JButton subButton = new JButton("Submit");

    private int wPosition = -1;
    private int bPosition = -1;

    public TextFrame() {

        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char wChar = wText.getText().charAt(0);
                char bChar = bText.getText().charAt(0);

                wPosition = (wChar >= 'A' && wChar <= 'H') ? wChar - 'A' : wChar - '0' - 1;
                bPosition = (bChar >= 'A' && bChar <= 'H') ? bChar - 'A' : bChar - '0' - 1;
                System.out.println(wPosition + " " + bPosition);
            }
        });
        jPanel.add(new JLabel("Black Gap"));
        jPanel.add(bText);
        jPanel.add(new JLabel("White Gap"));
        jPanel.add(wText);


        jPanel.add(subButton);
        this.add(label);
        this.add(jPanel);
//        this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        this.setModal(true);
        this.setSize(300, 100);
        this.setTitle("Please input your value only from 1 to 8 (or A to H)");
        this.pack();
        this.setVisible(true);

    }

    public Pair<Integer, Integer> getPosition(){
        return new Pair<>(wPosition, bPosition);
    }
}
