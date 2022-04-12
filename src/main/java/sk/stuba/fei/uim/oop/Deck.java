package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Deck {

    private Integer gameSize;
    private Tile[][] tiles;
    private List<Circle> circles;

    public Deck(Integer gameSize) {
        this.gameSize = gameSize;
        initializeDeck(gameSize);
        circles = new ArrayList<>(gameSize * gameSize);
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

        var initTile1 = tiles[gameSize / 2 - 1][gameSize / 2 - 1];
        var initCircle1 = new Circle(initTile1,
                initTile1.getXPos(),
                initTile1.getYPos(),
                initTile1.getTileSize(),
                Color.BLACK);
        initTile1.setCircle(initCircle1);

        var initTile2 = tiles[gameSize / 2 - 1][gameSize / 2];
        var initCircle2 = new Circle(initTile2,
                initTile2.getXPos(),
                initTile2.getYPos(),
                initTile2.getTileSize(),
                Color.WHITE);
        initTile2.setCircle(initCircle2);

        var initTile3 = tiles[gameSize / 2][gameSize / 2 - 1];
        var initCircle3 = new Circle(initTile3,
                initTile3.getXPos(),
                initTile3.getYPos(),
                initTile3.getTileSize(),
                Color.WHITE);
        initTile3.setCircle(initCircle3);

        var initTile4 = tiles[gameSize / 2][gameSize / 2];
        var initCircle4 = new Circle(initTile4,
                initTile4.getXPos(),
                initTile4.getYPos(),
                initTile4.getTileSize(),
                Color.BLACK);
        initTile4.setCircle(initCircle4);
    }

    public void makeMove(Tile tile, Color playerColor) {
        var circle = new Circle(tile,
                tile.getXPos(),
                tile.getYPos(),
                tile.getTileSize(),
                playerColor);
        tile.setCircle(circle);
        circles.add(circle);
    }

    public Tile getTile(int x, int y) {
        int tileSize = 600 / gameSize;
        x /= tileSize;
        y /= tileSize;
        return tiles[x][y];
    }
}
