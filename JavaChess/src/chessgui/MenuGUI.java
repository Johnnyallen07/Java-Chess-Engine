package chessgui;

import actions.ButtonActions;
import actions.MenuActions;
import maps.PiecePointMap;
import utils.Pair;
import utils.PieceColor;
import utils.PieceName;
import utils.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuGUI {
    public static void menus(JFrame f, JPanel jPanel, JMenuBar jb) {

        JMenu newMenu = new JMenu("New Game");


        JMenu m1 = new JMenu("Pawn Race");
        JMenuItem m2 = new JMenuItem("Standard Game");
        JMenu m3 = new JMenu("Play with AI");

        // randomize gap or specify the gap
        JMenuItem randomGap = new JMenuItem("Random Gap");
        randomGap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MenuActions(jPanel, f, new ArrayList<>()).initPawnRaceBoard((int) (Math.random() * 8), (int) (Math.random() * 8));
                f.validate();
            }
        });
        m1.add(randomGap);
        JMenuItem specGap = new JMenuItem("Spec Gap");
        specGap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextFrame textFrame = new TextFrame();

                Pair<Integer, Integer> position = textFrame.getPosition();

                new MenuActions(jPanel, f, new ArrayList<>()).initPawnRaceBoard(position.second, position.first);
                f.validate();

            }
        });

        m1.add(specGap);


        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuActions(jPanel, f, new ArrayList<>()).initStandardBoard(false, false);
                f.validate();
            }
        });

        JMenuItem level1 = new JMenuItem("Easy");
        level1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Point, Pair<PieceName, PieceColor>> map = PiecePointMap.getPointPieceNameandColorMap();
                PiecePointMap.initMap(map);

                new MenuActions(jPanel, f, new ArrayList<>()).initStandardBoard(true, false);
                MenuActions.setSearchDepth(1);
                f.validate();
            }
        });
        m3.add(level1);
        JMenuItem level2 = new JMenuItem("Medium");
        level2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Point, Pair<PieceName, PieceColor>> map = PiecePointMap.getPointPieceNameandColorMap();
                PiecePointMap.initMap(map);

                new MenuActions(jPanel, f, new ArrayList<>()).initStandardBoard(true, false);
                MenuActions.setSearchDepth(2);
                f.validate();
            }
        });
        m3.add(level2);
        JMenuItem level3 = new JMenuItem("Hard");
        level3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Point, Pair<PieceName, PieceColor>> map = PiecePointMap.getPointPieceNameandColorMap();
                PiecePointMap.initMap(map);

                new MenuActions(jPanel, f, new ArrayList<>()).initStandardBoard(true, false);
                MenuActions.setSearchDepth(4);
                f.validate();
            }
        });
        m3.add(level3);


        newMenu.add(m1);
        newMenu.add(m2);
        newMenu.add(m3);

        jb.add(newMenu);

        f.setJMenuBar(jb);
    }
}
