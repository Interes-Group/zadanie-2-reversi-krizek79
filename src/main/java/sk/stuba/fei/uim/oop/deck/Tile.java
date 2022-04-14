package sk.stuba.fei.uim.oop.deck;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.logic.GameLogic;
import sk.stuba.fei.uim.oop.utils.Connection;
import sk.stuba.fei.uim.oop.utils.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

@Getter
@Setter
public class Tile extends JPanel implements MouseListener {

    private final GameLogic gameLogic;
    private final Integer tileSize;
    private final Integer xPos;
    private final Integer yPos;
    private final Deck deck;

    private Boolean validated;
    private Boolean highlighted;
    private Map<Direction, Connection> neighbours;
    private Stone stone;
    private Color color;

    public Tile(Deck deck, GameLogic gameLogic, Integer tileSize, Integer x, Integer y) {
        this.deck = deck;
        this.gameLogic = gameLogic;
        this.tileSize = tileSize;
        this.xPos = x;
        this.yPos = y;
        this.validated = false;
        this.highlighted = false;
        this.neighbours = new HashMap<>();
        this.color = Color.PINK;
        setSize(new Dimension(tileSize, tileSize));
        setFocusable(true);
        addMouseListener(this);
    }

    public void addNeighbour(Tile tile, Direction direction) {
        neighbours.put(direction, new Connection(tile));
    }

    public Tile getNeighbourByDirection(Direction direction) {
        Connection c = neighbours.get(direction);
        if (c != null) {
            return c.getTile();
        } else return null;
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

//    public Boolean hasFriendAcross(Color playerColor) {
//        for (var neighbour: getAllNeighbours()) {
//            for (var dir: Direction.values()) {
//
//            }
//        }
//        return false;
//    }

    public void draw(Graphics g) {
        if (highlighted) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.PINK);
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
        g.fillOval(xPos + tileSize / 3, yPos + tileSize / 3,
                tileSize / 3, tileSize / 3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameLogic.isMoveValid(this, Color.WHITE)) {
            gameLogic.makeMove(this, Color.WHITE);
            gameLogic.getMenuPanel().getPlayerLabel().setText("Player: Black");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (validated) {
            highlighted = true;
            deck.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (validated) {
            highlighted = false;
            deck.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
