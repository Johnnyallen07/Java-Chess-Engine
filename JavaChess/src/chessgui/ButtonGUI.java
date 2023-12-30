package chessgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ButtonGUI {

    /***
     *
     * @param resources
     * @param idx
     *
     * @return
     * index of chessBoardSquare (same as JPanel)
     */
    public static JButton createButton(String resources, int idx) {
        if (resources == null) {
            return createButton(idx);
        }
        JButton b = new JButton();
        b.setMargin(new Insets(0, 0, 0, 0));
        // our chess pieces are 64x64 px in size, so we'll
        // 'fill this in' using a transparent icon
        ImageIcon icon = new ImageIcon(
                resources);
        if (idx % 2 == 0) {
            b.setBackground(Color.WHITE);
        } else {
            b.setBackground(Color.GRAY);
        }
        b.setIcon(icon);
        return b;
    }


    public static JButton createButton(int idx) {
        JButton b = new JButton();
        b.setMargin(new Insets(0, 0, 0, 0));
        // our chess pieces are 64x64 px in size, so we'll
        // 'fill this in' using a transparent icon
        ImageIcon icon = new ImageIcon(
                new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        if (idx % 2 == 0) {
            b.setBackground(Color.WHITE);
        } else {
            b.setBackground(Color.GRAY);
        }
        b.setIcon(icon);
        return b;
    }

    public static JButton[] getButtons(JPanel jPanel) {
        JButton[] bs = new JButton[8*8];
        int idx = 0;
        for (int i = 0; i < jPanel.getComponentCount(); i++) {
            if (jPanel.getComponent(i).getClass() == JButton.class) {
                bs[idx++] = (JButton) jPanel.getComponent(i);
            }
        }
        return bs;
    }


    public static void deleteAction(JButton b) {
        ActionListener[] actionListeners = b.getActionListeners();
        for (ActionListener actionListener : actionListeners){
            b.removeActionListener(actionListener);
        }

    }

    public static void deleteActions(JButton[] bs) {
        for (JButton b : bs) {
            deleteAction(b);
        }
    }


}
