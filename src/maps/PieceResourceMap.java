package maps;

import utils.Pair;
import utils.PieceColor;
import utils.PieceName;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PieceResourceMap {
    private static Map<Pair<PieceName, PieceColor>, String> pieceNameStringMap = new HashMap<>();

    static {

        String absoluteProjectPath = new File("").getAbsolutePath();

        pieceNameStringMap.put(new Pair<>(PieceName.bishop, PieceColor.WHITE),
                absoluteProjectPath+"\\src\\pieces\\bishop_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.pawn, PieceColor.WHITE),
                absoluteProjectPath+"\\src\\pieces\\pawn_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.king, PieceColor.WHITE),
                absoluteProjectPath+"\\src\\pieces\\king_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.knight, PieceColor.WHITE),
                absoluteProjectPath+"\\src\\pieces\\knight_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.queen, PieceColor.WHITE),
                absoluteProjectPath+"\\src\\pieces\\queen_white.png");
        pieceNameStringMap.put(new Pair<>(PieceName.rook, PieceColor.WHITE),
                absoluteProjectPath+"\\src\\pieces\\rook_white.png");



        pieceNameStringMap.put(new Pair<>(PieceName.bishop, PieceColor.BLACK),
                absoluteProjectPath+"\\src\\pieces\\bishop_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.pawn, PieceColor.BLACK),
                absoluteProjectPath+"\\src\\pieces\\pawn_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.king, PieceColor.BLACK),
                absoluteProjectPath+"\\src\\pieces\\king_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.knight, PieceColor.BLACK),
                absoluteProjectPath+"\\src\\pieces\\knight_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.queen, PieceColor.BLACK),
                absoluteProjectPath+"\\src\\pieces\\queen_black.png");
        pieceNameStringMap.put(new Pair<>(PieceName.rook, PieceColor.BLACK),
                absoluteProjectPath+"\\src\\pieces\\rook_black.png");

    }



    public static String filePlaceByName(PieceName name, PieceColor color){
        return filePlaceByName(new Pair<>(name, color));
    }
    public static String filePlaceByName(Pair<PieceName, PieceColor> pieceNamePieceColorPair){

        return pieceNameStringMap.get(pieceNamePieceColorPair);
    }
}