package chessgui;

import maps.PieceResourceMap;
import utils.*;
import utils.Point;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class ChessBoardGUI {
    private static final JButton[][] chessBoardSquares = new JButton[8][8];

    private static final String COLS = "ABCDEFGH";
    public static JPanel createChessBoard(){
        JPanel chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        chessBoard.setPreferredSize(new Dimension(640+64, 640));


        // create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                utils.Point coord = new Point(i, j);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
//                b.setPreferredSize(new Dimension(64, 64));
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);

                } else {
                    b.setBackground(Color.GRAY);

                }
                chessBoardSquares[j][i] = b;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int i = 0; i < 8; i++) {
            chessBoard.add(
                    new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    chessBoard.add(new JLabel(String.valueOf(i + 1),
                            SwingConstants.CENTER));
                }
                chessBoard.add(chessBoardSquares[i][j]);
            }
        }
        return chessBoard;
    }

    public static JButton[][] getChessBoardSquares(){
        return chessBoardSquares;
    }
}
