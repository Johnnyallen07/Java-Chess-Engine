package app;

import chessgui.MainGUI;

import javax.swing.*;

public class AppMain {
    // start a new Game!!!
    // start from NEW bar at left up corner
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                new MainGUI();
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
