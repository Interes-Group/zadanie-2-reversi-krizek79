package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.bot.Bot;
import sk.stuba.fei.uim.oop.frame.GameFrame;
import sk.stuba.fei.uim.oop.deck.Deck;
import sk.stuba.fei.uim.oop.deck.Stone;
import sk.stuba.fei.uim.oop.deck.Tile;
import sk.stuba.fei.uim.oop.menu.GameSizeSlider;
import sk.stuba.fei.uim.oop.menu.MenuPanel;
import sk.stuba.fei.uim.oop.utils.InputAdapter;

import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Getter
public class GameLogic extends InputAdapter {

    private final GameFrame gameFrame;
    private final MenuPanel menuPanel;
    private final Deck deck;
    private final Bot bot;

    private Integer gameSize;

    public GameLogic(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
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
        deck.findValidTiles(Color.WHITE);
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
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        restartGame(gameSize);
    }

    private void restartGame(Integer gameSize) {
        deck.initializeDeck(gameSize);
        menuPanel.getPlayerLabel().setText("Player: White");
        deck.findValidTiles(Color.WHITE);
    }

    public void makeMove(Tile tile, Color playerColor) {
        tile.setStone(new Stone(playerColor));
        tile.setHighlighted(false);
        tile.setValidated(false);
        deck.repaint();
    }

    public Boolean isMoveValid(Tile tile, Color playerColor) {
        return tile.getStone() == null
                && tile.neighbourHasEnemyStone(playerColor)
                && tile.hasFriendAcross(playerColor);
    }
}
