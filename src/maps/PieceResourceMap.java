package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;

import java.util.HashMap;
import java.util.Map;

public class PieceResourceMap {
    private static Map<Pair<PieceName, PieceColor>, String> pieceNameStringMap = new HashMap<>();

    static {

        pieceNameStringMap.put(new Pair<>(PieceName.bishop, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\bishop_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.pawn, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\pawn_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.king, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\king_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.knight, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\knight_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.queen, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\queen_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.rook, PieceColor.WHITE),
                "D:\\Desktop\\JavaChess\\src\\pieces\\rook_white.png");



        pieceNameStringMap.put(new Pair<>(PieceName.bishop, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\bishop_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.pawn, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\pawn_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.king, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\king_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.knight, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\knight_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.queen, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\queen_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.rook, PieceColor.BLACK),
                "D:\\Desktop\\JavaChess\\src\\pieces\\rook_black.png");

    }



    public static String filePlaceByName(PieceName name, PieceColor color){
        return filePlaceByName(new Pair<>(name, color));
    }
    public static String filePlaceByName(Pair<PieceName, PieceColor> pieceNamePieceColorPair){

        return pieceNameStringMap.get(pieceNamePieceColorPair);
    }
}