package chessgui;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class CenterText {

    public static void main(String[] args) {
        new CenterText();
    }

    public CenterText() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);

                frame.setVisible(true);
            }

        });
    }

    public class TestPane extends JPanel {

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);



            this.add(ChessBoardGUI.createChessBoard());
            int height = getHeight();
            String text = "This is a test xyx";

            g.setColor(Color.RED);
            g.drawLine(0, height / 2, getWidth(), height / 2);

            FontMetrics fm = g.getFontMetrics();
            int totalWidth = (fm.stringWidth(text) * 2) + 4;

            // Baseline
            int x = (getWidth() - totalWidth) / 2;
            int y = (getHeight() - fm.getHeight()) / 2;
            g.setColor(Color.BLACK);

            g.drawString(text, x, y + ((fm.getDescent() + fm.getAscent()) / 2));

            // Absolute...
            x += fm.stringWidth(text) + 2;
            y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
            g.drawString(text, x, y);


        }

    }

}