package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.ReverseList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PieceSquareTables {
    private static Map<Pair<PieceName, PieceColor>, int[][]> pstMap = new HashMap<>();

    public static void reverseColumnsInPlace(int[][] matrix) {
        for (int col = 0; col < matrix[0].length; col++) {
            for (int row = 0; row < matrix.length / 2; row++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[matrix.length - row - 1][col];
                matrix[matrix.length - row - 1][col] = temp;
            }
        }
    }

    static {
        int[][] pawns_white = {{0, 0, 0, 0, 0, 0, 0, 0},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {5, 5, 10, 25, 25, 10, 5, 5},
                {0, 0, 0, 20, 20, 0, 0, 0},
                {5, -5, -10, 0, 0, -10, -5, 5},
                {5, 10, 10, -20, -20, 10, 10, 5},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] pawns_black = {{0, 0, 0, 0, 0, 0, 0, 0},
                {5, 10, 10, -20, -20, 10, 10, 5},
                {5, -5, -10, 0, 0, -10, -5, 5},
                {0, 0, 0, 20, 20, 0, 0, 0},
                {5, 5, 10, 25, 25, 10, 5, 5},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] knights_white = {{-50, -40, -30, -30, -30, -30, -40, -50},
                {-40, -20, 0, 0, 0, 0, -20, -40},
                {-30, 0, 10, 15, 15, 10, 0, -30},
                {-30, 5, 15, 20, 20, 15, 5, -30},
                {-30, 0, 15, 20, 20, 15, 0, -30},
                {-30, 5, 10, 15, 15, 10, 5, -30},
                {-40, -20, 0, 5, 5, 0, -20, -40},
                {-50, -40, -30, -30, -30, -30, -40, -50}};
        int[][] knights_black = {
                {-50, -40, -30, -30, -30, -30, -40, -50},
                {-40, -20, 0, 5, 5, 0, -20, -40},
                {-30, 5, 10, 15, 15, 10, 5, -30},
                {-30, 0, 15, 20, 20, 15, 0, -30},
                {-30, 5, 15, 20, 20, 15, 5, -30},
                {-30, 0, 10, 15, 15, 10, 0, -30},
                {-40, -20, 0, 0, 0, 0, -20, -40},
                {-50, -40, -30, -30, -30, -30, -40, -50},
        };
        int[][] bishop_white = {
                {-20, -10, -10, -10, -10, -10, -10, -20},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-10, 0, 5, 10, 10, 5, 0, -10},
                {-10, 5, 5, 10, 10, 5, 5, -10},
                {-10, 0, 10, 10, 10, 10, 0, -10},
                {-10, 10, 10, 10, 10, 10, 10, -10},
                {-10, 5, 0, 0, 0, 0, 5, -10},
                {-20, -10, -10, -10, -10, -10, -10, -20},
        };
        int[][] bishop_black = {
                {-20, -10, -10, -10, -10, -10, -10, -20},
                {-10, 5, 0, 0, 0, 0, 5, -10},
                {-10, 10, 10, 10, 10, 10, 10, -10},
                {-10, 0, 10, 10, 10, 10, 0, -10},
                {-10, 5, 5, 10, 10, 5, 5, -10},
                {-10, 0, 5, 10, 10, 5, 0, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-20, -10, -10, -10, -10, -10, -10, -20},
        };
        int[][] rook_white = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {5, 10, 10, 10, 10, 10, 10, 5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {0, 0, 0, 5, 5, 0, 0, 0}};
        int[][] rook_black = {
                {0, 0, 0, 5, 5, 0, 0, 0},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {5, 10, 10, 10, 10, 10, 10, 5},
                {0, 0, 0, 0, 0, 0, 0, 0},};
        int[][] queens_white = {
                {-20, -10, -10, -5, -5, -10, -10, -20},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-10, 0, 5, 5, 5, 5, 0, -10},
                {-5, 0, 5, 5, 5, 5, 0, -5},
                {0, 0, 5, 5, 5, 5, 0, -5},
                {-10, 5, 5, 5, 5, 5, 0, -10},
                {-10, 0, 5, 0, 0, 0, 0, -10},
                {-20, -10, -10, -5, -5, -10, -10, -20}};
        int[][] queens_black = {
                {-20, -10, -10, -5, -5, -10, -10, -20},
                {-10, 0, 5, 0, 0, 0, 0, -10},
                {-10, 5, 5, 5, 5, 5, 0, -10},
                {0, 0, 5, 5, 5, 5, 0, -5},
                {-5, 0, 5, 5, 5, 5, 0, -5},
                {-10, 0, 5, 5, 5, 5, 0, -10},
                {-10, 0, 0, 0, 0, 0, 0, -10},
                {-20, -10, -10, -5, -5, -10, -10, -20}};
        int[][] kings_white = {
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-20, -30, -30, -40, -40, -30, -30, -20},
                {-10, -20, -20, -20, -20, -20, -20, -10},
                {20, 20, 0, 0, 0, 0, 20, 20},
                {20, 30, 10, 0, 0, 10, 30, 20}
        };
        int[][] kings_black = {
                {20, 30, 10, 0, 0, 10, 30, 20},
                {20, 20, 0, 0, 0, 0, 20, 20},
                {-10, -20, -20, -20, -20, -20, -20, -10},
                {-20, -30, -30, -40, -40, -30, -30, -20},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
                {-30, -40, -40, -50, -50, -40, -40, -30},
        };
        pstMap.put(new Pair<>(PieceName.pawn, PieceColor.WHITE), pawns_white);
        pstMap.put(new Pair<>(PieceName.pawn, PieceColor.BLACK), pawns_black);
        pstMap.put(new Pair<>(PieceName.bishop, PieceColor.WHITE), bishop_white);
        pstMap.put(new Pair<>(PieceName.bishop, PieceColor.BLACK), bishop_black);
        pstMap.put(new Pair<>(PieceName.king, PieceColor.WHITE), kings_white);
        pstMap.put(new Pair<>(PieceName.king, PieceColor.BLACK), kings_black);
        pstMap.put(new Pair<>(PieceName.knight, PieceColor.WHITE), knights_white);
        pstMap.put(new Pair<>(PieceName.knight, PieceColor.BLACK), knights_black);
        pstMap.put(new Pair<>(PieceName.rook, PieceColor.WHITE), rook_white);
        pstMap.put(new Pair<>(PieceName.rook, PieceColor.BLACK), rook_black);
        pstMap.put(new Pair<>(PieceName.queen, PieceColor.WHITE), queens_white);
        pstMap.put(new Pair<>(PieceName.queen, PieceColor.BLACK), queens_black);
    }

    public static Map<Pair<PieceName, PieceColor>, int[][]> getPstMap() {
        return pstMap;
    }
}
