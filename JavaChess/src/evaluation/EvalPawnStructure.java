package evaluation;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.Map;

public class EvalPawnStructure {

    public static int allPawnStructureScore(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        return doubledPawns(map, color) + chainedPawns(map, color);
    }

    // penalty for this doubled pawn structure
    public static int doubledPawn(int col, Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        int idx = -1;
        int count = 0;
        int sum = 0;
        for (int i = 0; i < 8; i++) {
            Point coord = new Point(i, col);
            if (map.get(coord).second == color){
                if (map.get(coord).first == PieceName.pawn){
                    if (idx == i - 1){
                        count += 5;

                    }
                    else {
                        sum += count * count;
                        count = 0;

                    }
                    idx = i;
                }
            }

        }
        sum += count * count;
        return sum;
    }

    public static int doubledPawns(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += doubledPawn(i, map, color);
        }
        return sum;
    }

    public static int chainedPawns(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        // check the pawn chain, diagonally
        int sum = 0;
        for (int col = 0; col < 8; col++) {
            for (int row = 1; row < 8; row++) {
                Point coord = new Point(row, col);
                while (map.get(coord).first == PieceName.pawn && map.get(coord).second == color && row > 0 && row < 7 && col < 7){

                    if (isPointPawn(map, color, new Point(row-1, col+1))){
                        sum += 1;
                        col++;
                        row--;
                        coord = new Point(row, col);
                    }
                    else if (isPointPawn(map, color, new Point(row+1, col+1))){
                        sum += 1;
                        col++;
                        row++;
                        coord = new Point(row, col);
                    }
                    else{
                        break;
                    }

                }
            }
        }
        return sum;
    }

    private static boolean isPointPawn(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color, Point point){
        return map.get(point).first == PieceName.pawn && map.get(point).second == color;
    }

    public static int isolatedPawn(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        int sum = 0;
        int col = 0;
        while(col < 8){
            for (int row = 1; row < 8; row++) {
                Point coord = new Point(row, col);
                if (map.get(coord).first == PieceName.pawn && map.get(coord).second == color && row > 0){
                    if (col < 7) col++;

                    if (isPointPawn(map, color, new Point(row-1, col))){
                        sum += 1;
                        row -= 2;
                    }


                    else if (isPointPawn(map, color, new Point(row+1, col))){
                        sum += 1;
                    }

                }
            }
        }

        return switch (sum) {
            case 1 -> 5;
            case 2 -> 10;
            case 3 -> 20;
            case 4, 5, 6, 7 -> 40;
            default -> 0;
        };
    }
}
