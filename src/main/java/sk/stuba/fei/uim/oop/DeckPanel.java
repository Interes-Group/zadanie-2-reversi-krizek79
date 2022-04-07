package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class DeckPanel extends JPanel {

    private Integer gameSize;

    public DeckPanel(Integer gameSize) {
        this.gameSize = gameSize;
        setPreferredSize(new Dimension(600, 600));
        setLayout(new GridLayout(gameSize, gameSize));
        setBackground(Color.LIGHT_GRAY);
        setFocusable(true);

        generateDeck(gameSize);
    }

    public void generateDeck(Integer gameSize) {
        int tileSize = 600 / gameSize;
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                add(new TilePanel(tileSize, null, j * tileSize));
            }
        }
    }
}
