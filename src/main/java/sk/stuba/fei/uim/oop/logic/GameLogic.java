package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.frame.GameFrame;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.deck.Stone;
import sk.stuba.fei.uim.oop.deck.Tile;
import sk.stuba.fei.uim.oop.menu.GameSizeSlider;
import sk.stuba.fei.uim.oop.menu.MenuPanel;
import sk.stuba.fei.uim.oop.util.InputAdapter;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameLogic extends InputAdapter {

    @Getter
    private final MenuPanel menuPanel;
    @Getter
    private final Deck deck;
    @Getter
    private final List<Stone> stones;
    private final Bot bot;
    private Tile currentTile;

    private Integer gameSize;

    public GameLogic(GameFrame gameFrame) {
        this.stones = new ArrayList<>();
        this.menuPanel = new MenuPanel();
        this.gameSize = menuPanel.getGameSizeSlider().getValue();
        this.deck = new Deck(gameSize, this);
        this.bot = new Bot(this);

        gameFrame.add(menuPanel);
        gameFrame.add(deck);

        menuPanel.getGameSizeSlider().addChangeListener(this);
        menuPanel.getGameResetButton().addActionListener(this);
        menuPanel.addKeyListener(this);

        deck.addKeyListener(this);
    }

    private void restartGame(Integer gameSize) {
        deck.initializeDeck(gameSize);
        stones.clear();
        menuPanel.getPlayerLabel().setForeground(Color.WHITE);
        menuPanel.getPlayerLabel().setText("White");
        deck.findValidTiles(Color.WHITE);
    }

    public void makeMove(Tile tile, Color playerColor) {
        var stone = new Stone(playerColor);
        stones.add(stone);
        tile.setStone(stone);
        tile.setHighlighted(false);
        tile.setValidated(false);
        deck.flipStones(tile, playerColor);
        deck.repaint();
    }

    public Boolean isMoveValid(Tile tile, Color playerColor) {
        return tile != null
                && tile.getStone() == null
                && tile.neighbourHasEnemyStone(playerColor)
                && tile.hasFriendAcross(playerColor);
    }

    public void endGame() {
        System.out.println(stones.size());
        menuPanel.getPlayerLabel().setForeground(new Color(0, 80, 0));
        int white = 0;
        int black = 0;
        for (var stone: stones) {
            if (stone.getColor().equals(Color.BLACK)) black++;
            if (stone.getColor().equals(Color.WHITE)) white++;
        }

        if (black > white) {
            menuPanel.getPlayerLabel().setText("Black (" + black + "-" +  white + ")");
        }
        if (black < white) {
            menuPanel.getPlayerLabel().setText("White (" + black + "-" +  white + ")");
        }
        if (black == white) {
            menuPanel.getPlayerLabel().setForeground(Color.ORANGE);
            menuPanel.getPlayerLabel().setText("Tie");
        }
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
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int x = e.getX();
        int y = e.getY();
        var tile = (Tile) e.getComponent().getComponentAt(x, y);
        if (isMoveValid(tile, Color.WHITE)) {
            makeMove(tile, Color.WHITE);
            getMenuPanel().getPlayerLabel().setForeground(Color.BLACK);
            getMenuPanel().getPlayerLabel().setText("Black");
            deck.findValidTiles(Color.BLACK);
            if (!deck.getValidTiles().isEmpty()) {
                bot.makeRandomMove();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        int x = e.getX();
        int y = e.getY();
        currentTile = (Tile) e.getComponent().getComponentAt(x, y);
        if (currentTile.getValidated()) {
            currentTile.setHighlighted(true);
            deck.repaint();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (currentTile.getValidated()) {
            currentTile.setHighlighted(false);
            deck.repaint();
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
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        restartGame(gameSize);
    }
}
