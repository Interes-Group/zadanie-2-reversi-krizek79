package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class Render extends JPanel {

    private final Deck deck;

    public Render(Deck deck) {
        this.deck = deck;
        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(600, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.deck.draw(g);
    }
}
