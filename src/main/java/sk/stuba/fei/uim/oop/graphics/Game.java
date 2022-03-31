package sk.stuba.fei.uim.oop.graphics;

import sk.stuba.fei.uim.oop.graphics.panel.GamePanel;

import javax.swing.*;

public class Game extends JFrame{

    public Game() {
        super();
        setTitle("Reversi");
        setContentPane(new GamePanel());
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
