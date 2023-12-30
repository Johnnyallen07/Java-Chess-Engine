package evaluation;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.List;
import java.util.Map;

public class EvalBoard {
    // evaluate
    public static int score(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        int blackScore = allCriteria(map, PieceColor.BLACK);
        int whiteScore = allCriteria(map, PieceColor.WHITE);
        return color == PieceColor.BLACK ?  blackScore - whiteScore : whiteScore - blackScore;
    }

    private static int allCriteria(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        return EvalPawnStructure.allPawnStructureScore(map, color) + EvalPiece.sumPiece(map, color) + EvalPosition.evalPositionOfAllPieces(map, color);
    }
}
