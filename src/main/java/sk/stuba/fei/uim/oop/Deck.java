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

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (i != 0) {
                    tiles[i][j].addNeighbour(tiles[i-1][j], Direction.UP);
                    if (j != 0) {
                        tiles[i][j].addNeighbour(tiles[i-1][j-1], Direction.UP_LEFT);
                    }
                    if (j != gameSize - 1) {
                        tiles[i][j].addNeighbour(tiles[i-1][j+1], Direction.UP_RIGHT);
                    }
                }
                if (i != gameSize - 1) {
                    tiles[i][j].addNeighbour(tiles[i+1][j], Direction.DOWN);
                    if (j != 0) {
                        tiles[i][j].addNeighbour(tiles[i+1][j-1], Direction.DOWN_LEFT);
                    }
                    if (j != gameSize - 1) {
                        tiles[i][j].addNeighbour(tiles[i+1][j+1], Direction.DOWN_RIGHT);
                    }
                }
                if (j != 0) {
                    tiles[i][j].addNeighbour(tiles[i][j-1], Direction.LEFT);
                }
                if (j != gameSize - 1) {
                    tiles[i][j].addNeighbour(tiles[i][j+1], Direction.RIGHT);
                }
            }
        }

        var initTile1 = tiles[gameSize / 2 - 1][gameSize / 2 - 1];
        initTile1.setStone(new Stone(Color.BLACK));
        var initTile2 = tiles[gameSize / 2 - 1][gameSize / 2];
        initTile2.setStone(new Stone(Color.WHITE));
        var initTile3 = tiles[gameSize / 2][gameSize / 2 - 1];
        initTile3.setStone(new Stone(Color.WHITE));
        var initTile4 = tiles[gameSize / 2][gameSize / 2];
        initTile4.setStone(new Stone(Color.BLACK));
    }

    public Tile getTile(int x, int y) {
        if (x > 0 && x < 600 && y > 0 && y < 600) {
            int tileSize = 600 / gameSize;
            x /= tileSize;
            y /= tileSize;
            return tiles[x][y];
        } else return null;
    }
}
