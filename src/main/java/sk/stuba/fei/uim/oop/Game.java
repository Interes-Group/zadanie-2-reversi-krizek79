package sk.stuba.fei.uim.oop;

import javax.swing.*;

public class Game {

    public Game() {
        JFrame frame = new JFrame("Reversi");
        frame.setContentPane(new GamePanel());
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
