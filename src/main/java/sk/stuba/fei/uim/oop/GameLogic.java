package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.event.ChangeEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@Getter
public class GameLogic extends InputAdapter {

    private final GameFrame gameFrame;
    private final MenuPanel menuPanel;
    private final DeckPanel deckPanel;

    private Integer gameSize;

    public GameLogic(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.menuPanel = new MenuPanel();
        this.gameSize = menuPanel.getGameSizeSlider().getValue();
        this.deckPanel = new DeckPanel(gameSize);
        deckPanel.initializeDeck(gameSize);

        gameFrame.add(menuPanel);
        gameFrame.add(deckPanel);

        menuPanel.getGameSizeSlider().addChangeListener(this);
        menuPanel.addKeyListener(this);
        deckPanel.addKeyListener(this);
        gameFrame.addMouseListener(this);
        gameFrame.addMouseMotionListener(this);
    }

    private void restartGame(Integer gameSize) {
        deckPanel.initializeDeck(gameSize);
        deckPanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyTyped(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            restartGame(6);
            menuPanel.getGameSizeSlider().setValue(6);
            gameSize = 6;
            menuPanel.getGameSizeLabel().setText("Size: " + gameSize);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);
        gameSize = ((GameSizeSlider) e.getSource()).getValue();
        menuPanel.getGameSizeLabel().setText("Size: " + gameSize);
        restartGame(gameSize);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
    }
}
