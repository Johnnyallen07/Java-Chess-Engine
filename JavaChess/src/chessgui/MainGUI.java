package chessgui;

import actions.ButtonActions;
import actions.MenuActions;
import utils.Pair;
import utils.PieceColor;
import maps.PiecePointMap;
import utils.PieceName;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.*;

import utils.Point;

public class MainGUI {
    private final static JFrame f = new JFrame("ChessEngine");

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    private final JLabel message = new JLabel(
            "Chess is ready to play!");


    private static final JMenuBar jb = new JMenuBar();

    public MainGUI() {
        initializeGui();
    }

    public final void initializeGui() {

        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
//        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        // return boardView:


        JPanel chessBoard = ChessBoardGUI.createChessBoard();
        JButton[][] chessBoardSquares = ChessBoardGUI.getChessBoardSquares();
        MenuGUI.menus(f, chessBoard, jb);
        Map<Point, Pair<PieceName, PieceColor>> map = PiecePointMap.getPointPieceNameandColorMap();
        PiecePointMap.initMap(map);
        ButtonActions.addActionsToButtons(chessBoardSquares, chessBoard, f, map);


        gui.add(chessBoard);

        f.add(this.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

    }

    public final JComponent getGui() {
        return gui;
    }


}
