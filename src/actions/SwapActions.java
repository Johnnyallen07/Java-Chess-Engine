package actions;

import chessgui.ButtonGUI;
import maps.PiecePointMap;
import maps.PieceResourceMap;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwapActions {
    public static void replace(JFrame f, JPanel jPanel, JButton b, Point point){
        int row = point.getRow();
        int col = point.getCol();
        jPanel.remove(9 * (row+1) + col + 1);
        jPanel.add(b, (9 * (row+1) + col + 1));
    }

    public static void repaint(JFrame f, JPanel jPanel, Map<Point, Pair<PieceName, PieceColor>> map){

        for (Point point : map.keySet()) {
            PieceName name = map.get(point).first;
            PieceColor color = map.get(point).second;
            int row = point.getRow();
            int col = point.getCol();
            String source = PieceResourceMap.filePlaceByName(name, color);
            int idx = 9 * (row+1) + col + 1;
            jPanel.remove(idx);
            JButton button = ButtonGUI.createButton(source, idx);
            ButtonActions.addActionsToButton(button, jPanel, f);
            jPanel.add(button, idx);
        }
        f.validate();

    }

    public static void replaceTo(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        PieceName fromName = map.get(from).first;
        PieceColor fromColor = map.get(from).second;
        map.put(to, new Pair<PieceName, PieceColor>(fromName, fromColor));
        map.put(from, new Pair<PieceName, PieceColor>(PieceName.grid, PieceColor.NULL));
    }

    public static PieceColor switchColor(PieceColor color) {
        if (color == PieceColor.BLACK) {
            return PieceColor.WHITE;
        } else return PieceColor.BLACK;
    }
}
