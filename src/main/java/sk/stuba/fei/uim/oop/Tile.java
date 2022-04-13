package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
@Setter
public class Tile {

    private final Integer tileSize;
    private final Integer xPos;
    private final Integer yPos;

    private Boolean hasValidPoint;
    private Map<Direction, Connection> neighbours;
    private Stone stone;
    private Color color;

    public Tile(Integer tileSize, Integer x, Integer y) {
        this.tileSize = tileSize;
        this.xPos = x;
        this.yPos = y;
        hasValidPoint = false;
        neighbours = new HashMap<>();
        this.color = Color.PINK;
    }

    public void addNeighbour(Tile tile, Direction direction) {
        neighbours.put(direction, new Connection(tile));
    }

    public List<Tile> getAllNeighbours() {
        List<Tile> neighbourList = new ArrayList<>();
        neighbours.values().forEach(connection -> neighbourList.add(connection.getTile()));
        return neighbourList;
    }

    public Boolean neighbourHasEnemyStone(Color stoneColor) {
        for (var tile: getAllNeighbours()) {
            if (tile.getStone() != null && !tile.getStone().getColor().equals(stoneColor)) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, tileSize, tileSize);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, tileSize, tileSize);

        if (hasValidPoint) {
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
        g.setColor(Color.RED);
        g.fillOval(xPos + tileSize / 3, yPos + tileSize / 3,
                tileSize / 3, tileSize / 3);
    }
}
