package evaluation;

import maps.ScoreMap;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.Map;

public class EvalPiece {
    // evaluate one color on the board:
    public static int sumPiece(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        int sum = 0;
        Map<PieceName, Integer> scoreMap = ScoreMap.getScoreMap();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Point coord = new Point(i, j);
                if (map.get(coord).second == color){
                    PieceName name = map.get(coord).first;
                    if (scoreMap.containsKey(name)){
                        sum += scoreMap.get(name);
                    }
                }
            }
        }

//        System.out.println("The piece ("+color.name()+") sum is: " + sum);
//        if (color == PieceColor.BLACK) return -sum;
        return sum;
    }


}
