package chessgui;

import maps.PiecePointMap;
import utils.*;
import utils.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class BoardRepaint {
    public static void repaintAll(JPanel jPanel, Map<Point, Pair<PieceName, PieceColor>> map) {
        Component[] components = jPanel.getComponents();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                int idx = (row + 1) * 9 + col + 1;
                JButton b = (JButton) components[idx];
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
//                b.setPreferredSize(new Dimension(64, 64));
                if ((row % 2 == 1 && col % 2 == 1) || (row % 2 == 0 && col % 2 == 0)) {
                    b.setBackground(Color.WHITE);

                } else {
                    b.setBackground(Color.GRAY);

                }
                map.put(new Point(row, col),
                        new Pair<PieceName, PieceColor>(PieceName.grid, PieceColor.NULL));
            }

        }
    }
}
