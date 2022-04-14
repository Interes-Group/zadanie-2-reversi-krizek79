package sk.stuba.fei.uim.oop.deck;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.logic.GameLogic;
import sk.stuba.fei.uim.oop.utils.Direction;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class Deck extends JPanel {

    private final GameLogic gameLogic;
    private Integer gameSize;
    private Tile[][] tiles;
    private GridLayout gridLayout;

    public Deck(Integer gameSize, GameLogic gameLogic) {
        this.gameSize = gameSize;
        this.gameLogic = gameLogic;
        setPreferredSize(new Dimension(600, 600));
        gridLayout = new GridLayout();
        setLayout(gridLayout);

        initializeDeck(gameSize);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[j][i].draw(g);
            }
        }
    }

    public void initializeDeck(int gameSize) {
        this.removeAll();
        setGameSize(gameSize);
        int tileSize = 600 / gameSize;
        gridLayout.setColumns(gameSize);
        gridLayout.setRows(gameSize);
        gridLayout.setHgap(0);
        gridLayout.setVgap(0);
        tiles = new Tile[gameSize][gameSize];

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                var tile = new Tile(this, gameLogic, tileSize, j * tileSize, i * tileSize);
                add(tile);
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

        validate();
    }

    public void findValidTiles(Color playerColor) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[i][j].setValidated(false);
            }
        }

        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (tiles[i][j].neighbourHasEnemyStone(playerColor)
                        && tiles[i][j].getStone() == null
                        && tiles[i][j].hasFriendAcross(playerColor)) {
                    tiles[i][j].setValidated(true);
                    repaint();
                }
            }
        }
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
