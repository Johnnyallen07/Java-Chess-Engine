package actions;

import maps.PiecePointMap;
import maps.PieceResourceMap;
import utils.*;
import chessgui.BoardRepaint;
import chessgui.ButtonGUI;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuActions {

    private static String gameMode = "";
    private static boolean isBlackAI = false;

    private static int SEARCH_DEPTH = 1;

    private final JPanel chessBoard;
    private final JFrame f;

    private final String absoluteProjectPath = new File("").getAbsolutePath();

    private final Map<Point, Pair<PieceName, PieceColor>> map;




    public MenuActions(JPanel chessBoard, JFrame f, List<Point> recordList) {
        this.chessBoard = chessBoard;
        this.f = f;
        map = PiecePointMap.getPointPieceNameandColorMap();
    }

    // add chess pieces to chessBoard
    public void initPawnRaceBoard(int blackGap, int whiteGap) {
        gameMode = "pawn-race";
        BoardRepaint.repaintAll(chessBoard, map);
        f.validate();

        // black init

        int row = 1;
                for (int col = 0; col < 8; col++) {
            if (col == blackGap) continue;
            JButton b = ButtonGUI.createButton(absoluteProjectPath+"/src/pieces/pawn_black.png", row + col);
            chessBoard.remove(9 * (row+1) + col + 1);
            chessBoard.add(b, (9 * (row+1) + col + 1));
            // update b before take actions
            ButtonActions.addActionsToButton(b, chessBoard, f);

            map.put(new Point(row, col), new Pair<>(PieceName.pawn, PieceColor.BLACK));
        }

        // white init
        row = 6;

        for (int col = 0; col < 8; col++) {
            if (col == whiteGap) continue;
            JButton b = ButtonGUI.createButton(absoluteProjectPath+"/src/pieces/pawn_white.png", row + col);
            chessBoard.remove(9 * (row+1) + col + 1);
            chessBoard.add(b, (9 * (row+1) + col + 1));
            // update b before take actions
            ButtonActions.addActionsToButton(b, chessBoard, f);

            map.put(new Point(row, col), new Pair<>(PieceName.pawn, PieceColor.WHITE));
        }
        ButtonActions.initColor();
        ButtonActions.initENPASSENTPawns();
    }

    public void initStandardBoard(boolean blackAI, boolean whiteAI){
        gameMode = "standard";
        isBlackAI = blackAI;
        BoardRepaint.repaintAll(chessBoard, map);
        f.validate();
        // black init
        int row = 0;
        PieceColor color = PieceColor.BLACK;
        JButton b = new JButton();
        PieceName name = PieceName.grid;



        for (int col = 0; col < 8; col++) {
            row = 0;
            name = PieceName.grid;

            switch (col) {
                case 0, 7 -> {
                    name = PieceName.rook;
                    b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                    map.put(new Point(row, col), new Pair<>(name, color));
                }
                case 1, 6 -> {
                    name = PieceName.knight;
                    b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                    map.put(new Point(row, col), new Pair<>(name, color));
                }
                case 2, 5 -> {
                    name = PieceName.bishop;
                    b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                    map.put(new Point(row, col), new Pair<>(name, color));
                }
                case 3 -> {
                    name = PieceName.queen;
                    b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                    map.put(new Point(row, col), new Pair<>(name, color));
                }
                case 4 -> {
                    name = PieceName.king;
                    b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                    map.put(new Point(row, col), new Pair<>(name, color));
                }
            }
            SwapActions.replace(f, chessBoard, b, new Point(row, col));
            ButtonActions.addActionsToButton(b, chessBoard, f);
            row = 1;
            b = ButtonGUI.createButton(absoluteProjectPath+"/src/pieces/pawn_black.png", row + col);
            SwapActions.replace(f, chessBoard, b, new Point(row, col));
            // update b before take actions
            ButtonActions.addActionsToButton(b, chessBoard, f);
            map.put(new Point(row, col), new Pair<>(PieceName.pawn, PieceColor.BLACK));

        }

        // white init

        color = PieceColor.WHITE;

        for (int col = 0; col < 8; col++) {
            row = 7;
            name = PieceName.grid;
            switch (col){
            case 0, 7 -> {
                name = PieceName.rook;
                b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                map.put(new Point(row, col), new Pair<>(name, color));
            }
            case 1, 6 -> {
                name = PieceName.knight;
                b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                map.put(new Point(row, col), new Pair<>(name, color));
            }
            case 2, 5 -> {
                name = PieceName.bishop;
                b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                map.put(new Point(row, col), new Pair<>(name, color));
            }
            case 3 -> {
                name = PieceName.king;
                b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                map.put(new Point(row, col), new Pair<>(name, color));
            }
            case 4 -> {
                name = PieceName.queen;
                b = ButtonGUI.createButton(PieceResourceMap.filePlaceByName(name, color), row+col);
                map.put(new Point(row, col), new Pair<>(name, color));
            }
        }
        SwapActions.replace(f, chessBoard, b, new Point(row, col));
        ButtonActions.addActionsToButton(b, chessBoard, f);
        row = 6;
            b = ButtonGUI.createButton(absoluteProjectPath+"/src/pieces/pawn_white.png", row + col);
        SwapActions.replace(f, chessBoard, b, new Point(row, col));
        // update b before take actions
        ButtonActions.addActionsToButton(b, chessBoard, f);
        map.put(new Point(row, col), new Pair<>(PieceName.pawn, PieceColor.WHITE));
        }
        ButtonActions.initColor();
        ButtonActions.initENPASSENTPawns();
    }

    public static String getGameMode(){
        return gameMode;
    }

    public static boolean getAI(){
        return isBlackAI;
    }

    public static int getSearchDepth(){
        return SEARCH_DEPTH;
    }

    public static void setSearchDepth(int depth){
        SEARCH_DEPTH = depth;
    }

}
