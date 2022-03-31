package sk.stuba.fei.uim.oop.graphics.panel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class GamePanel extends JPanel {

    private final int width = 800;
    private final int height = 600;

    public GamePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.MAGENTA);
        var menuPanel = new MenuPanel();
        add(menuPanel);
        var deckPanel = new DeckPanel();
        add(deckPanel);
    }
}
