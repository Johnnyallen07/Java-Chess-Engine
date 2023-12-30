package chessai;

import actions.MoveActions;
import actions.SwapActions;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.*;

public class PossibleMove {
    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleSingleMove(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleBoard = new ArrayList<>();
        allPossibleBoard.addAll(allPossiblePawnMoves(map, color));
        allPossibleBoard.addAll(allPossibleBishopMoves(map, color));

        List<Map<Point, Pair<PieceName, PieceColor>>> legalQueenMove = allPossibleQueenMoves(map, color);
        if (legalQueenMove != null) allPossibleBoard.addAll(legalQueenMove);

        List<Map<Point, Pair<PieceName, PieceColor>>> legalKingMove = allPossibleKingMoves(map, color);
        if (legalKingMove != null) allPossibleBoard.addAll(legalKingMove);

        allPossibleBoard.addAll(allPossibleKnightMoves(map, color));
        allPossibleBoard.addAll(allPossibleRookMoves(map, color));
        return allPossibleBoard;
    }

    private static void validPawnMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> oriBoard,
                                      List<Map<Point, Pair<PieceName, PieceColor>>> pawnList) {

        if (MoveActions.isValidPawnMove(from, to, oriBoard)) {
            SwapActions.replaceTo(from, to, oriBoard);
            pawnList.add(oriBoard);

        }


    }

    private static void validQueenMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> oriBoard,
                                       List<Map<Point, Pair<PieceName, PieceColor>>> queenList) {

        if (MoveActions.isValidQueenMove(from, to, oriBoard)) {
            SwapActions.replaceTo(from, to, oriBoard);
            queenList.add(oriBoard);
        }

    }

    private static void validRookMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> oriBoard,
                                      List<Map<Point, Pair<PieceName, PieceColor>>> rookList) {

        if (MoveActions.isValidRookMove(from, to, oriBoard)) {
            SwapActions.replaceTo(from, to, oriBoard);
            rookList.add(oriBoard);

        }
    }

    private static void validBishopMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> oriBoard,
                                        List<Map<Point, Pair<PieceName, PieceColor>>> bishopList) {
        if (MoveActions.isValidBishopMove(from, to, oriBoard)) {
            SwapActions.replaceTo(from, to, oriBoard);
            bishopList.add(oriBoard);

        }
    }

    private static void validKingMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> oriBoard,
                                      List<Map<Point, Pair<PieceName, PieceColor>>> kingList) {
        if (MoveActions.isValidKingMove(from, to, oriBoard)) {
            SwapActions.replaceTo(from, to, oriBoard);
            kingList.add(oriBoard);

        }
    }

    private static void validKnightMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> oriBoard,
                                        List<Map<Point, Pair<PieceName, PieceColor>>> knightList) {
        if (MoveActions.isValidKnightMove(from, to, oriBoard)) {
            SwapActions.replaceTo(from, to, oriBoard);
            knightList.add(oriBoard);

        }
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> possiblePawnMove(Point from, Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Map<Point, Pair<PieceName, PieceColor>>> allPawnList = new ArrayList<>();
        int xFrom = from.getRow();
        int yFrom = from.getCol();
        // check 1,2 forward, and two diagonals
        if (color == PieceColor.BLACK) {
            validPawnMove(from, new Point(xFrom + 1, yFrom), deepCopy(map), allPawnList);
            validPawnMove(from, new Point(xFrom + 2, yFrom), deepCopy(map), allPawnList);
            validPawnMove(from, new Point(xFrom + 1, yFrom - 1), deepCopy(map), allPawnList);
            validPawnMove(from, new Point(xFrom + 1, yFrom + 1), deepCopy(map), allPawnList);
        } else {
            validPawnMove(from, new Point(xFrom - 1, yFrom), deepCopy(map), allPawnList);
            validPawnMove(from, new Point(xFrom - 2, yFrom), deepCopy(map), allPawnList);
            validPawnMove(from, new Point(xFrom - 1, yFrom - 1), deepCopy(map), allPawnList);
            validPawnMove(from, new Point(xFrom - 1, yFrom + 1), deepCopy(map), allPawnList);
        }
        return allPawnList;
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossiblePawnMoves(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Map<Point, Pair<PieceName, PieceColor>>> allPawnList = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Point coord = new Point(row, col);
                if (map.get(coord).first == PieceName.pawn && map.get(coord).second == color) {
                    allPawnList.addAll(possiblePawnMove(coord, map, color));
                }
            }
        }
        return allPawnList;
    }

    private static List<Point> horizontalMotion(Point from) {
        List<Point> horizontalList = new ArrayList<>();
        int row = from.getRow();
        for (int col = 0; col < 8; col++) {
            horizontalList.add(new Point(row, col));
        }
        return horizontalList;
    }

    private static List<Point> verticalMotion(Point from) {
        List<Point> verticalList = new ArrayList<>();
        int col = from.getCol();
        for (int row = 0; row < 8; row++) {
            verticalList.add(new Point(row, col));
        }
        return verticalList;
    }

    private static List<Point> diagonalMotion(Point from) {
        List<Point> diagonalList = new ArrayList<>();
        int row = from.getRow();
        int col = from.getCol();
        int copyRow = row;
        int copyCol = col;
        // left up:
        while (copyRow >= 0 && copyCol >= 0 && copyRow < 8 && copyCol < 8) {
            diagonalList.add(new Point(copyRow--, copyCol--));
        }
        // right up:
        copyRow = row;
        copyCol = col;
        while (copyRow >= 0 && copyCol >= 0 && copyRow < 8 && copyCol < 8) {
            diagonalList.add(new Point(copyRow++, copyCol++));
        }
        copyRow = row;
        copyCol = col;
        while (copyRow >= 0 && copyCol >= 0 && copyRow < 8 && copyCol < 8) {
            diagonalList.add(new Point(copyRow--, copyCol++));
        }
        copyRow = row;
        copyCol = col;
        while (copyRow >= 0 && copyCol >= 0 && copyRow < 8 && copyCol < 8) {
            diagonalList.add(new Point(copyRow++, copyCol--));
        }
        return diagonalList;
    }

    private static List<Point> getKey(Map<Point, Pair<PieceName, PieceColor>> map, Pair<PieceName, PieceColor> value) {
        List<Point> pointList = new ArrayList<>();
        Set<Point> points = map.keySet();
        for (Point point : points) {
            if (map.get(point).equals(value)) {
                pointList.add(point);
            }
        }
        return pointList;
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleBishopMoves(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Point> fromList = getKey(map, new Pair<>(PieceName.bishop, color));
        List<Map<Point, Pair<PieceName, PieceColor>>> allBishopList = new ArrayList<>();
        for (Point from : fromList) {
            List<Point> diagonalList = diagonalMotion(from);
            for (Point to : diagonalList) {
                validBishopMove(from, to, deepCopy(map), allBishopList);
            }
        }
        return allBishopList;
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleQueenMoves(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Point> queenList = getKey(map, new Pair<>(PieceName.queen, color));
        if (queenList.size() == 0) return null;
        Point from = queenList.get(0);
        List<Map<Point, Pair<PieceName, PieceColor>>> allQueenList = new ArrayList<>();
        List<Point> horizontalList = horizontalMotion(from);
        List<Point> verticalList = verticalMotion(from);
        List<Point> diagonalList = diagonalMotion(from);
        for (Point to : horizontalList) {
            validQueenMove(from, to, deepCopy(map), allQueenList);
        }
        for (Point to : verticalList) {
            validQueenMove(from, to, deepCopy(map), allQueenList);
        }
        for (Point to : diagonalList) {
            validQueenMove(from, to, deepCopy(map), allQueenList);
        }

        return allQueenList;
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleRookMoves(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {

        List<Point> fromList = getKey(map, new Pair<>(PieceName.rook, color));
        List<Map<Point, Pair<PieceName, PieceColor>>> allRookList = new ArrayList<>();
        for (Point from : fromList) {
            List<Point> horizontalList = horizontalMotion(from);
            List<Point> verticalList = verticalMotion(from);
            for (Point to : horizontalList) {
                validRookMove(from, to, deepCopy(map), allRookList);
            }
            for (Point to : verticalList) {
                validRookMove(from, to, deepCopy(map), allRookList);
            }
        }
        return allRookList;
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleKingMoves(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Point> kingList = getKey(map, new Pair<>(PieceName.king, color));
        if (kingList.size() == 0) return null;
        Point from = kingList.get(0);
        List<Map<Point, Pair<PieceName, PieceColor>>> allKingList = new ArrayList<>();
        int row = from.getRow();
        int col = from.getCol();
        validKingMove(from, new Point(row - 1, col - 1), deepCopy(map), allKingList);
        validKingMove(from, new Point(row - 1, col), deepCopy(map), allKingList);
        validKingMove(from, new Point(row - 1, col + 1), deepCopy(map), allKingList);
        validKingMove(from, new Point(row, col - 1), deepCopy(map), allKingList);
        validKingMove(from, new Point(row, col), deepCopy(map), allKingList);
        validKingMove(from, new Point(row, col + 1), deepCopy(map), allKingList);
        validKingMove(from, new Point(row + 1, col - 1), deepCopy(map), allKingList);
        validKingMove(from, new Point(row + 1, col), deepCopy(map), allKingList);
        validKingMove(from, new Point(row + 1, col + 1), deepCopy(map), allKingList);
        return allKingList;
    }

    public static List<Map<Point, Pair<PieceName, PieceColor>>> allPossibleKnightMoves(Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        List<Point> fromList = getKey(map, new Pair<>(PieceName.knight, color));
        List<Map<Point, Pair<PieceName, PieceColor>>> allKnightList = new ArrayList<>();

        for (Point from : fromList) {
            int row = from.getRow();
            int col = from.getCol();
            validKnightMove(from, new Point(row - 2, col - 1), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row - 1, col - 2), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row + 1, col - 2), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row + 2, col - 1), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row - 1, col + 2), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row - 2, col + 1), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row + 2, col + 1), deepCopy(map), allKnightList);
            validKnightMove(from, new Point(row + 1, col + 2), deepCopy(map), allKnightList);
        }
        return allKnightList;
    }


    private static Map<Point, Pair<PieceName, PieceColor>> deepCopy(Map<Point, Pair<PieceName, PieceColor>> originalMap) {
        Map<Point, Pair<PieceName, PieceColor>> copyMap = new HashMap<>();
        Set<Point> points = originalMap.keySet();
        for (Point point : points) {
            copyMap.put(point, originalMap.get(point));
        }
        return copyMap;
    }


}
