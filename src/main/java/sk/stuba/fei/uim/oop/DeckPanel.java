package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class DeckPanel extends JPanel {

    private Integer gameSize;
    private TilePanel[][] tiles;

    public DeckPanel(Integer gameSize) {
        this.gameSize = gameSize;
        setPreferredSize(new Dimension(600, 600));
        setLayout(new GridLayout(gameSize, gameSize));
        setBackground(Color.green);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[i][j].draw(g);
            }
        }
    }

    public void initializeDeck(int gameSize) {
        setGameSize(gameSize);
        int tileSize = 600 / gameSize;
        tiles = new TilePanel[gameSize][gameSize];
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                var tilePanel = new TilePanel(tileSize, j * tileSize, i * tileSize);
                tiles[j][i] = tilePanel;
                add(tilePanel);
                System.out.println("TileX: " + tiles[j][i].getXPos() + " TileY: " + tiles[j][i].getYPos());
            }
        }
        System.out.println("----------------------------------");
    }
}
