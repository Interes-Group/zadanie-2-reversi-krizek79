package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Setter
@Getter
public class Deck {

    private Integer gameSize;
    private Tile[][] tiles;

    public Deck(Integer gameSize) {
        this.gameSize = gameSize;
        initializeDeck(gameSize);
    }

    public void draw(Graphics g) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[j][i].draw(g);
            }
        }
    }

    public void initializeDeck(int gameSize) {
        setGameSize(gameSize);
        int tileSize = 600 / gameSize;
        tiles = new Tile[gameSize][gameSize];
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                var tile = new Tile(tileSize, j * tileSize, i * tileSize);
                tiles[j][i] = tile;
            }
        }
    }

    public Tile getTilePanel(int x, int y) {
        int tileSize = 600 / gameSize;
        x = (x - 200) / tileSize;
        y /= tileSize;
        return tiles[x][y];
    }
}
