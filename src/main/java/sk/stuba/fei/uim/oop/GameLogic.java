package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

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

        findValidTiles(Color.WHITE);
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
        var tile = deck.getTile(x, y);
        if (tile != null) {
            if (isMoveValid(tile, Color.WHITE)) {
                makeMove(tile, Color.WHITE);
                menuPanel.getPlayerLabel().setText("Player: Black");
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int x = e.getX();
        int y = e.getY();
        var tile = deck.getTile(x, y);
        if (tile != null) {
            System.out.println(new Point(tile.getXPos(), tile.getYPos()));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        restartGame(gameSize);
    }

    private void restartGame(Integer gameSize) {
        deck.initializeDeck(gameSize);
        menuPanel.getPlayerLabel().setText("Player: White");
        findValidTiles(Color.WHITE);
    }

    private void makeMove(Tile tile, Color playerColor) {
        tile.setStone(new Stone(playerColor));
        render.repaint();
    }

    private Boolean isMoveValid(Tile tile, Color playerColor) {
        if (tile.getStone() == null && tile.neighbourHasEnemyStone(playerColor)) {
            return true;
        } else return false;
    }

    public void findValidTiles(Color stoneColor) {
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                if (deck.getTiles()[i][j].neighbourHasEnemyStone(stoneColor)) {
                    deck.getTiles()[i][j].setHasValidPoint(true);
                    render.repaint();
                }
            }
        }
    }
}
