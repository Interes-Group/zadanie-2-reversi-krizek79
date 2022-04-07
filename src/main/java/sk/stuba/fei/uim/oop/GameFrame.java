package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        super();
        setTitle("Reversi");
        setResizable(false);

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.MAGENTA);

        new GameLogic(this);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}