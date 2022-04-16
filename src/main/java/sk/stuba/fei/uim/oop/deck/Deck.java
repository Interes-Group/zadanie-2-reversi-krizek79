package sk.stuba.fei.uim.oop.deck;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Deck extends JPanel {

    private final GameLogic gameLogic;
    @Getter
    private final ArrayList<Tile> validTiles;
    private final Random random;
    private final GridLayout gridLayout;
    @Setter
    private Integer gameSize;
    private Tile[][] tiles;

    public Deck(Integer gameSize, GameLogic gameLogic) {
        this.gameSize = gameSize;
        this.gameLogic = gameLogic;
        setPreferredSize(new Dimension(600, 600));
        gridLayout = new GridLayout();
        setLayout(gridLayout);
        random = new Random();
        validTiles = new ArrayList<>();

        initializeDeck(gameSize);
        findValidTiles(Color.WHITE);
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
                var tile = new Tile(gameLogic, tileSize, j * tileSize, i * tileSize);
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

        var startTile1 = tiles[gameSize / 2 - 1][gameSize / 2 - 1];
        startTile1.setStone(new Stone(Color.BLACK));
        gameLogic.getStones().add(startTile1.getStone());

        var startTile2 = tiles[gameSize / 2 - 1][gameSize / 2];
        startTile2.setStone(new Stone(Color.WHITE));
        gameLogic.getStones().add(startTile2.getStone());

        var startTile3 = tiles[gameSize / 2][gameSize / 2 - 1];
        startTile3.setStone(new Stone(Color.WHITE));
        gameLogic.getStones().add(startTile3.getStone());

        var startTile4 = tiles[gameSize / 2][gameSize / 2];
        startTile4.setStone(new Stone(Color.BLACK));
        gameLogic.getStones().add(startTile4.getStone());

        validate();
    }

    private void setAllTilesUnvalidated() {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                tiles[i][j].setValidated(false);
            }
        }
        validTiles.clear();
    }

    public void findValidTiles(Color playerColor) {
        setAllTilesUnvalidated();
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (tiles[i][j].neighbourHasEnemyStone(playerColor)
                        && tiles[i][j].getStone() == null
                        && tiles[i][j].hasFriendAcross(playerColor)) {
                    tiles[i][j].setValidated(true);
                    validTiles.add(tiles[i][j]);
                    repaint();
                }
            }
        }
    }

    public void flipStones(Tile tile, Color playerColor) {
        ArrayList<Tile> flippables = new ArrayList<>();
        for (var dir: Direction.values()) {
            ArrayList<Tile> tempTiles = new ArrayList<>();
            var neighbour = tile.getNeighbourByDirection(dir);
            if (neighbour != null
                    && neighbour.getStone() != null
                    && neighbour.getStone().getColor() != playerColor) {
                tempTiles.add(neighbour);
                neighbour = neighbour.getNeighbourByDirection(dir);
                while (neighbour != null && neighbour.getStone() != null) {
                    if (neighbour.getStone().getColor().equals(playerColor)) {
                        flippables.addAll(tempTiles);
                        flippables.forEach(s -> s.getStone().setColor(playerColor));
                        break;
                    } else {
                        tempTiles.add(neighbour);
                        neighbour = neighbour.getNeighbourByDirection(dir);
                    }
                }
            }
        }
    }

    public Tile getRandomValidTile() {
        if (!validTiles.isEmpty()) {
            return validTiles.get(random.nextInt(validTiles.size()));
        } else return null;
    }
}
