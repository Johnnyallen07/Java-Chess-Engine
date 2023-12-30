package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;

import java.util.HashMap;
import java.util.Map;

public class ScoreMap {
    private static Map<PieceName, Integer> scoreMap = new HashMap<>();

    static {
        scoreMap.put(PieceName.pawn, 100);
        scoreMap.put(PieceName.knight, 320);
        scoreMap.put(PieceName.bishop, 330);
        scoreMap.put(PieceName.rook, 500);
        scoreMap.put(PieceName.queen, 900);
        scoreMap.put(PieceName.king, 20000);
    }

    public static Map<PieceName, Integer> getScoreMap(){
        return scoreMap;
    }
}
