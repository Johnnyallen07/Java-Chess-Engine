package evaluation;

import maps.PieceSquareTables;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.Map;

public class EvalPosition {
    private static final Map<Pair<PieceName, PieceColor>, int[][]> pstMap = PieceSquareTables.getPstMap();

    public static int evalPositionOfPiece(Point point, Map<Point, Pair<PieceName, PieceColor>> map){
        return pstMap.get(map.get(point))[point.getRow()][point.getCol()];
    }

    public static int evalPositionOfAllPieces(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        int sum = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Point coord = new Point(row, col);
                if (map.get(coord).second == color){
                    sum += evalPositionOfPiece(coord, map);
                }
            }
        }
        return sum;
    }
}
