package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Getter
public class GameLogic extends InputAdapter {

    private final GameFrame gameFrame;
    private final MenuPanel menuPanel;
    private final Deck deck;
    private final Render render;

    private Integer gameSize;

    public GameLogic(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.menuPanel = new MenuPanel();
        this.gameSize = menuPanel.getGameSizeSlider().getValue();
        this.deck = new Deck(gameSize);
        this.render = new Render(deck);

        gameFrame.add(menuPanel);
        gameFrame.add(render);

        menuPanel.getGameSizeSlider().addChangeListener(this);
        menuPanel.getGameResetButton().addActionListener(this);
        menuPanel.addKeyListener(this);

        render.addKeyListener(this);
        render.addMouseListener(this);
        render.addMouseMotionListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyTyped(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            restartGame(gameSize);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);
        gameSize = ((GameSizeSlider) e.getSource()).getValue();
        menuPanel.getGameSizeLabel().setText("Size: " + gameSize + "x" + gameSize);
        restartGame(gameSize);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int x = e.getX();
        int y = e.getY();
        var tile = getTile(x, y);
        if (tile != null) {
            if (tile.getCircle() == null) {
                makeMove(tile, Color.WHITE);
                render.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int x = e.getX();
        int y = e.getY();
        var tile = getTile(x, y);
        if (tile != null) {
            System.out.println(new Point(tile.getXPos(), tile.getYPos()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        restartGame(gameSize);
    }

    private Tile getTile(int x, int y) {
        if (x > 0 && x < 600 && y > 0 && y < 600) {
            return this.getDeck().getTile(x, y);
        } else return null;
    }

    private void restartGame(Integer gameSize) {
        deck.initializeDeck(gameSize);
        render.repaint();
    }

    public void makeMove(Tile tile, Color playerColor) {
        var circle = new Circle(tile,
                tile.getXPos(),
                tile.getYPos(),
                tile.getTileSize(),
                playerColor);
        tile.setCircle(circle);
        getDeck().getCircles().add(circle);
    }
}
