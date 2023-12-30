package actions;


import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class WinActions{
    public static boolean isPawnRaceWin(Point point, PieceColor color, String gameMode){
        if (!gameMode.equals("pawn-race")) return false;
        if (color == PieceColor.WHITE){
            return point.getRow() == 0 ;
        }
        else return point.getRow() == 7;
    }

    public static boolean isStandardWin(Map<Point, Pair<PieceName, PieceColor>> map, String gameMode){
        if (!gameMode.equals("standard")) return false;
        int count = 0;
        Set<Point> points = map.keySet();
        for (Point point : points){
            if (map.get(point).first == PieceName.king){
                count++;
            }
        }
        return count != 2;
    }
}
