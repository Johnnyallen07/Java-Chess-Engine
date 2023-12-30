package actions;

import maps.PiecePointMap;
import utils.*;
import chessgui.ButtonGUI;
import maps.PieceResourceMap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import utils.Point;

public class MoveActions {

    // 0 means white, 1 means black

    // convert 2D in chessboardsqaure to index of component of Jpanel

    private static boolean isEnPassent(Point from, Point to){
        return (Math.abs(from.getRow() - to.getRow()) == 1 && Math.abs(from.getCol() - to.getCol()) == 1);
    }
    public static void move(JPanel jp, JFrame f, Map<Point, Pair<PieceName, PieceColor>> map, List<Point> recordList) {

        Point from = recordList.get(0);
        Point to = recordList.get(1);
        int xFrom = from.getRow();
        int yFrom = from.getCol();
        int xTo = to.getRow();
        int yTo = to.getCol();
        // calculator index in jp

        int fromIndex = (xFrom + 1) * 9 + (yFrom + 1);

        int toIndex = (xTo + 1) * 9 + (yTo + 1);

        if (isEnPassent(from, to) && map.get(from).first == PieceName.pawn && map.get(to).first == PieceName.grid){
            deletePiece(new Point(xFrom, yTo), jp, map);
        }

        // get resources each other

        String resourseFrom = PieceResourceMap.filePlaceByName(map.get(recordList.get(0)));
        String resourseTo = PieceResourceMap.filePlaceByName(map.get(recordList.get(1)));
        JButton fromButton = ButtonGUI.createButton(/*resourseTo,*/ fromIndex);
        JButton toButton = ButtonGUI.createButton(resourseFrom, toIndex);

        // update jbuttons
        jp.remove(fromIndex);
        jp.add(fromButton, fromIndex);
        jp.remove(toIndex);
        jp.add(toButton, toIndex);

        // add actions to button
        ButtonActions.addActionsToButton(fromButton, jp, f);
        ButtonActions.addActionsToButton(toButton, jp, f);

        // update map
        Pair<PieceName, PieceColor> infoFrom = map.get(recordList.get(0));

        map.put(recordList.get(0), new Pair<>(PieceName.grid, PieceColor.NULL));
        map.put(recordList.get(1), infoFrom);

    }

    public static boolean isValidPiece(Point point, Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color) {
        PieceName aPiece = map.get(point).first;
        PieceColor aColor = map.get(point).second;
        return aPiece != PieceName.grid && aColor == color;
    }

    public static boolean isAnotherPiece(Point point, Map<Point, Pair<PieceName, PieceColor>> map, PieceColor color){
        PieceColor aColor = map.get(point).second;
        return aColor == color;
    }

    public static boolean isValidMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
         PieceName name = map.get(from).first;
        return switch (name) {
            case pawn -> isValidPawnMove(from, to, map);
            case queen -> isValidQueenMove(from, to, map);
            case rook -> isValidRookMove(from, to, map);
            case bishop -> isValidBishopMove(from, to, map);
            case knight -> isValidKnightMove(from, to, map);
            case king -> isValidKingMove(from, to, map);
            default -> false;
        };
    }



    // replace piece as simple grid
    private static void deletePiece(Point point, JPanel jPanel, Map<Point, Pair<PieceName, PieceColor>> map){
        int row = point.getRow();
        int col = point.getCol();
        JButton b = (JButton) jPanel.getComponent((row + 1) * 9 + col + 1);
        b.setMargin(new Insets(0,0,0,0));
        // our chess pieces are 64x64 px in size, so we'll
        // 'fill this in' using a transparent icon
        ImageIcon icon = new ImageIcon(
                new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

        if ((row + col) % 2 == 0) {
            b.setBackground(Color.WHITE);
        } else {
            b.setBackground(Color.GRAY);
        }
        b.setIcon(icon);
        map.put(point, new Pair<>(PieceName.grid, PieceColor.NULL));

    }

    public static boolean isValidPawnMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        int xFrom = from.getRow();
        int yFrom = from.getCol();
        int xTo = to.getRow();
        int yTo = to.getCol();
        if (xFrom < 0 || xFrom > 7 || xTo < 0 || xTo > 7 || yFrom < 0 || yFrom > 7 || yTo < 0 || yTo > 7) return false;
        boolean[][] recordENPASSENTPawns = ButtonActions.getRecordENPASSENTPawns();
        PieceColor fromColor = map.get(from).second;
        PieceColor toColor = map.get(to).second;
        // PEACEFUL
        if (yFrom == yTo && isEmptyVertical(from, to, map)){
            if (map.get(to).first != PieceName.grid) return false;
            if (fromColor == PieceColor.BLACK){
                // a special case: when pawn starts from the origin
                return (xFrom == 1 && xTo - xFrom == 2) || xTo - xFrom == 1;
            }
            else {
                return (xFrom == 6 && xFrom - xTo == 2) || xFrom - xTo == 1;
            }
        }
        // CAPTURE
        else if (Math.abs(yFrom - yTo) == 1){
            if (fromColor == PieceColor.BLACK){
                // EN-PASSENT
                // black case
                if (xFrom == 4 && xTo - xFrom == 1){
                    Point whitePosition = new Point(xFrom, yTo);
                    if (map.get(whitePosition).second == PieceColor.WHITE &&
                            map.get(whitePosition).first == PieceName.pawn &&
                    recordENPASSENTPawns[yTo][0]){
                        return true;
                    }
                }
                return (toColor == PieceColor.WHITE && xTo - xFrom == 1);
            }
            else{
                // EN-PASSENT
                // white case
                if (xFrom == 3 && xFrom - xTo == 1){
                    Point blackPosition = new Point(xFrom, yTo);
                    if (map.get(blackPosition).second == PieceColor.BLACK &&
                            map.get(blackPosition).first == PieceName.pawn &&
                            recordENPASSENTPawns[yTo][1]){
//                        deletePiece(blackPosition, jPanel, map);
                        return true;
                    }
                }
                return toColor == PieceColor.BLACK && xFrom - xTo == 1;
            }

        }


        return false;
    }



    public static boolean isValidQueenMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        // queen can move vertical / horizontal / diagonal
        // check whether the path is empty?

//        System.out.println(isEmptyPath(from, to, map, "horizontal"));
//        System.out.println(isEmptyPath(from, to, map, "diagonal"));
//        System.out.println(isEmptyPath(from, to, map, "vertical"));
//        System.out.println(map.get(from).second != map.get(to).second);

        return (isEmptyPath(from, to, map, "vertical") || isEmptyPath(from, to, map, "horizontal") ||
                isEmptyPath(from, to, map, "diagonal")) && map.get(from).second != map.get(to).second;
    }



    private static boolean isEmptyPath(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map, String mode){
        return switch (mode) {
            case "horizontal" -> isEmptyHorizon(from, to, map);
            case "vertical" -> isEmptyVertical(from, to, map);
            case "diagonal" -> isEmptyDiagonal(from, to, map);
            default -> false;
        };
    }

    private static boolean isEmptyHorizon(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        if (from.getRow() != to.getRow()) return false;
        int row = from.getRow();
        int fromCol = from.getCol();
        int toCol = to.getCol();
        if (fromCol > toCol){
            int swap = fromCol;
            fromCol = toCol;
            toCol = swap;
        }
        for (int col = fromCol + 1; col < toCol; col++) {
             if (map.get(new Point(row, col)).first != PieceName.grid){
                 return false;
             }
        }

        return true;
    }

    private static boolean isEmptyVertical(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        if (from.getCol() != to.getCol()) return false;
        int col = from.getCol();
        int fromRow = from.getRow();
        int toRow = to.getRow();
        if (fromRow > toRow){
            int swap = fromRow;
            fromRow = toRow;
            toRow = swap;
        }
        for (int row = fromRow + 1; row < toRow; row++) {
            if (map.get(new Point(row, col)).first != PieceName.grid){
                return false;
            }
        }
        return true;
    }

    private static boolean isEmptyDiagonal(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int toRow = to.getRow();
        int toCol = to.getCol();

        if (Math.abs(fromCol - toCol) != Math.abs(fromRow - toRow)) return false;
        int colInterval = fromCol < toCol ? 1 : -1;
        int rowInterval = fromRow < toRow ? 1 : -1;

        int num = Math.abs(fromCol - toCol);

        for (int i = 0; i < num - 1; i++) {
            fromRow += rowInterval;
            fromCol += colInterval;
//            System.out.println(map.get(new Point(fromRow, fromCol)).first);
            if (map.get(new Point(fromRow, fromCol)).first != PieceName.grid){
                return false;
            }

        }
        return true;
    }

    public static boolean isValidRookMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        return (isEmptyPath(from, to, map, "vertical") || isEmptyPath(from, to, map, "horizontal"))
                && map.get(from).second != map.get(to).second;
    }

    public static boolean isValidBishopMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        return isEmptyDiagonal(from, to, map)
                && map.get(from).second != map.get(to).second;
    }

    public static boolean isValidKnightMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map){
        int fromRow = from.getRow();
        int fromCol = from.getCol();

        int toRow = to.getRow();
        int toCol = to.getCol();
        if (toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7) return false;

        int rowDiff = Math.abs(fromRow - toRow);
        int colDiff = Math.abs(fromCol - toCol);

        // only two combinations: (1,2) and (2,1), in difference:
        return ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) && map.get(from).second != map.get(to).second;
    }

    public static boolean isValidKingMove(Point from, Point to, Map<Point, Pair<PieceName, PieceColor>> map) {
        int fromRow = from.getRow();
        int fromCol = from.getCol();
        int toRow = to.getRow();
        int toCol = to.getCol();
        if (toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7) return false;
        int rowDiff = Math.abs(fromRow - toRow);
        int colDiff = Math.abs(fromCol - toCol);
        return (rowDiff == 1 || rowDiff == 0) && (colDiff == 1 || colDiff == 0) && map.get(from).second != map.get(to).second;
    }

}
