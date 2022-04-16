package sk.stuba.fei.uim.oop.deck;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.logic.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
public class Tile extends JPanel {

    private final Integer tileSize;
    private final Integer xPos;
    private final Integer yPos;
    private final Map<Direction, Tile> neighbours;

    @Getter
    @Setter
    private Boolean validated;
    @Setter
    private Boolean highlighted;
    @Getter
    @Setter
    private Stone stone;

    public Tile(GameLogic gameLogic, Integer tileSize, Integer x, Integer y) {
        this.tileSize = tileSize;
        this.xPos = x;
        this.yPos = y;
        this.validated = false;
        this.highlighted = false;
        this.neighbours = new HashMap<>();
        setSize(new Dimension(tileSize, tileSize));
        setFocusable(true);
        addMouseListener(gameLogic);
    }

    public void addNeighbour(Tile tile, Direction direction) {
        neighbours.put(direction, tile);
    }

    public Tile getNeighbourByDirection(Direction direction) {
        return neighbours.get(direction);
    }

    private List<Tile> getAllNeighbours() {
        return new ArrayList<>(neighbours.values());
    }

    public Boolean neighbourHasEnemyStone(Color stoneColor) {
        for (var tile: getAllNeighbours()) {
            if (tile.getStone() != null && !tile.getStone().getColor().equals(stoneColor)) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasFriendAcross(Color playerColor) {
        for (var dir: Direction.values()) {
            var neighbour = getNeighbourByDirection(dir);
            if (neighbour != null
                    && neighbour.getStone() != null
                    && neighbour.getStone().getColor() != playerColor) {
                neighbour = neighbour.getNeighbourByDirection(dir);
                while (neighbour != null && neighbour.getStone() != null) {
                    if (neighbour.getStone().getColor().equals(playerColor)) {
                        return true;
                    } else {
                        neighbour = neighbour.getNeighbourByDirection(dir);
                    }
                }
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        if (highlighted) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.LIGHT_GRAY);
        }

        g.fillRect(xPos, yPos, tileSize, tileSize);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, tileSize, tileSize);

        if (validated) {
            drawValidPoint(g);
        }

        drawStone(g);
    }

    private void drawStone(Graphics g) {
        if (stone != null) {
            g.setColor(stone.getColor());
            g.fillOval(xPos + 10, yPos + 10, tileSize - 20, tileSize - 20);
            g.setColor(Color.BLACK);
            g.drawOval(xPos + 10, yPos + 10, tileSize - 20, tileSize - 20);
        }
    }

    private void drawValidPoint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillOval(xPos + tileSize / 3 + 1, yPos + tileSize / 3 + 1,
                tileSize / 3, tileSize / 3);
    }
}
