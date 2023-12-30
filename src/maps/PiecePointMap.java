package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.HashMap;
import java.util.Map;

public class PiecePointMap {
    private static Map<Point, Pair<PieceName, PieceColor>> pointPieceNameandColorMap = new HashMap<>();

    public static Map<Point, Pair<PieceName, PieceColor>> getPointPieceNameandColorMap() {
        return pointPieceNameandColorMap;
    }

    public static void setPointPieceNameandColorMap(Map<Point, Pair<PieceName, PieceColor>> map){
        pointPieceNameandColorMap = map;
    }
    public static void initMap(Map<Point, Pair<PieceName, PieceColor>> map){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map.put(new Point(i, j), new Pair<PieceName, PieceColor>(PieceName.grid, PieceColor.NULL));
            }
        }

    }
}

