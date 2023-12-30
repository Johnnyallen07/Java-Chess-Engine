package actions;

import chessai.Minimax;
import chessai.PossibleMove;
import chessgui.BoardRepaint;
import chessgui.ButtonGUI;
import chessgui.WinGUI;
import evaluation.EvalBoard;
import evaluation.EvalPawnStructure;
import evaluation.EvalPosition;
import maps.PiecePointMap;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ButtonActions {


    private static PieceColor color;

    private static List<Point> fromToPointList = new ArrayList<>();

    private static boolean[][] recordENPASSENTPawns = new boolean[8][2];


    public static List<Point> getFromToPointList(){
        return fromToPointList;
    }
    public static void addActionsToButtons(JButton[][] jButtons, JPanel jPanel, JFrame f, Map<Point, Pair<PieceName, PieceColor>> map) {
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[0].length; j++) {
                addActionsToButton(jButtons[i][j], jPanel, f);
            }
        }
    }

    public static boolean[][] getRecordENPASSENTPawns(){
        return recordENPASSENTPawns;
    }

    public static void initENPASSENTPawns(){
        recordENPASSENTPawns = new boolean[8][2];
    }


    public static void addActionsToButton(JButton b, JPanel jPanel, JFrame f){
        b.addActionListener(new ActionListener() {
            // add move action to each button
            public void actionPerformed(ActionEvent e) {
                Map<Point, Pair<PieceName, PieceColor>> map = PiecePointMap.getPointPieceNameandColorMap();
//                System.out.println(fromToPointList);
                if (fromToPointList.size() == 1) {

                    // sometimes choose a wrong piece, no worries, you can re-choose another valid piece
                    if (MoveActions.isAnotherPiece(ButtonActions.pieceAt(jPanel, b), map, color)) {
                        fromToPointList.remove(0);
                        fromToPointList.add(ButtonActions.pieceAt(jPanel, b));
                    } else {
                        Point from = fromToPointList.get(0);
                        Point to = ButtonActions.pieceAt(jPanel, b);

                        if (MoveActions.isValidMove(from, to, map)) {


                            if (Math.abs(from.getRow() - to.getRow()) == 2){
                                if (color == PieceColor.WHITE){
                                    recordENPASSENTPawns[to.getCol()][0] = true;
                                }
                                else recordENPASSENTPawns[to.getCol()][1] = true;
                            }

                            fromToPointList.add(ButtonActions.pieceAt(jPanel, b));
                            MoveActions.move(jPanel, f, map, fromToPointList);
                            f.validate();
                            fromToPointList.remove(1);
                            fromToPointList.remove(0);



                            // turn for AI:
                            if (MenuActions.getAI()){
                                switchColor();
                                Map<Point, Pair<PieceName, PieceColor>> bestMove = new Minimax(color, map, MenuActions.getSearchDepth()).decideMove();
                                PiecePointMap.setPointPieceNameandColorMap(bestMove);
//                                map = PiecePointMap.getPointPieceNameandColorMap();
//                                System.out.println(EvalBoard.score(bestMove, PieceColor.BLACK));
                                SwapActions.repaint(f, jPanel, bestMove);
                                if (WinActions.isStandardWin(bestMove, MenuActions.getGameMode())) {
                                    new WinGUI();
//                                    ButtonGUI.deleteActions(ButtonGUI.getButtons(jPanel));
                                }
                            }
                            else{
                                if (WinActions.isStandardWin(map, MenuActions.getGameMode())) {
                                    new WinGUI();
//                                    ButtonGUI.deleteActions(ButtonGUI.getButtons(jPanel));

                                }
                                // end game of pawn race:
                                if (WinActions.isPawnRaceWin(to, color, MenuActions.getGameMode())) {
                                    new WinGUI();
//                                ButtonGUI.deleteActions(ButtonGUI.getButtons(jPanel));

                                }
                            }



                            // test evaluation part: only for AI

//                            EvalBoard.score(map, color);
//                            EvalPawnStructure.chainedPawns(map, color);
//                            System.out.println("The bonus chain pawn ("+color.name()+") sum is: " + EvalPosition.evalPositionOfAllPieces(map, color));

                            // test all possible moves: only for chess AI
//                            List<Map<Point, Pair<PieceName, PieceColor>>> maps1 = PossibleMove.allPossiblePawnMoves(map, color);
//                            List<Map<Point, Pair<PieceName, PieceColor>>> maps2 = PossibleMove.allPossibleQueenMoves(map, color);
//                            List<Map<Point, Pair<PieceName, PieceColor>>> maps3 = PossibleMove.allPossibleRookMoves(map, color);
//                            List<Map<Point, Pair<PieceName, PieceColor>>> maps4 = PossibleMove.allPossibleBishopMoves(map, color);
//                            List<Map<Point, Pair<PieceName, PieceColor>>> maps5 = PossibleMove.allPossibleKingMoves(map, color);
//                            System.out.println(maps5);
//                            List<Map<Point, Pair<PieceName, PieceColor>>> maps6 = PossibleMove.allPossibleKnightMove(map, color);


                            switchColor();
                        }
                    }


                } else {
                    if (MoveActions.isValidPiece(ButtonActions.pieceAt(jPanel, b), map, color)) {
//                        System.out.println(ButtonActions.pieceAt(jPanel, b));

                        fromToPointList.add(ButtonActions.pieceAt(jPanel, b));

                    }

                }



            }
        });
    }

    private static void switchColor() {
        if (color == PieceColor.BLACK) {
            color = PieceColor.WHITE;
        } else color = PieceColor.BLACK;
    }

    public static void initColor() {
        color = PieceColor.WHITE;
    }

    public static Point pieceAt(JPanel jPanel, JButton b) {
        int idx = 0;
        Component[] components = jPanel.getComponents();
        for (int i = 0; i < components.length; i++) {
            if (b == components[i]) {
                idx = i;
                break;
            }
        }
        int row = idx / 9 - 1;
        int col = idx % 9 - 1;
        return new Point(row, col);
    }

}
