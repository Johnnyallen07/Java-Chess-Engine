package chessai;

import actions.SwapActions;
import evaluation.EvalBoard;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import java.util.List;
import java.util.Map;

public class Minimax {
    private int SEARCH_DEPTH;
    private PieceColor color;
    private final Map<Point, Pair<PieceName, PieceColor>> board;

    private Map<Point, Pair<PieceName, PieceColor>> bestMove;

    public Minimax(PieceColor color, Map<Point, Pair<PieceName, PieceColor>> board, int SEARCH_DEPTH){
        this.color = color;
        this.board = board;
        this.SEARCH_DEPTH = SEARCH_DEPTH;
    }


    public Map<Point, Pair<PieceName, PieceColor>> decideMove(){
        maximizer(board, SEARCH_DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestMove;
    }

    private int maximizer(Map<Point, Pair<PieceName, PieceColor>> board, int depth, int alpha, int beta){
        if (depth == 0){
            return EvalBoard.score(board, PieceColor.BLACK);
        }
        List<Map<Point, Pair<PieceName, PieceColor>>> legalMoves = PossibleMove.allPossibleSingleMove(board, color);
        for(Map<Point, Pair<PieceName, PieceColor>> singleMove : legalMoves){
            color = SwapActions.switchColor(color);
            int score = minimizer(singleMove, depth-1, alpha, beta);
            color = SwapActions.switchColor(color);
            if (score > alpha){
                alpha = score;
                if (depth == SEARCH_DEPTH){
                    bestMove = singleMove;
                }
            }

            if (alpha >= beta){
                return alpha;
            }
        }
        return alpha;
    }

    private int minimizer(Map<Point, Pair<PieceName, PieceColor>> board, int depth, int alpha, int beta){
        if (depth == 0){
            return EvalBoard.score(board, PieceColor.WHITE);
        }
        List<Map<Point, Pair<PieceName, PieceColor>>> legalMoves = PossibleMove.allPossibleSingleMove(board, color);
        for(Map<Point, Pair<PieceName, PieceColor>> singleMove : legalMoves){
            color = SwapActions.switchColor(color);
            int score = maximizer(singleMove, depth-1, alpha, beta);
            color = SwapActions.switchColor(color);
            if (score <= beta){
                beta = score;
            }

            if (alpha >= beta){
                return beta;
            }
        }
        return beta;
    }


}
