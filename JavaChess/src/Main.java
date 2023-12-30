import actions.SwapActions;
import maps.PieceSquareTables;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Pair<PieceName, PieceColor>, int[][]> pstMap = PieceSquareTables.getPstMap();
        System.out.println(pstMap);

    }

}