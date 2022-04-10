package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.event.ChangeEvent;
import java.awt.*;
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
        menuPanel.addKeyListener(this);
        gameFrame.addKeyListener(this);
        gameFrame.addMouseListener(this);
        gameFrame.addMouseMotionListener(this);
    }

    private void restartGame(Integer gameSize) {
        deck.initializeDeck(gameSize);
        render.repaint();
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
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        int x = e.getX() - 10;
        int y = e.getY() - 30;
        if (e.getX() > 200 && e.getX() < 800 && e.getY() > 0 && e.getY() < 630) {
            var tile = this.getDeck().getTilePanel(x, y);
            var circle = new Circle(tile, tile.getXPos(), tile.getYPos(), tile.getTileSize(), Color.WHITE);
            tile.setCircle(circle);
            render.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int x = e.getX() - 10;
        int y = e.getY() - 30;
        System.out.println(e.getPoint());
        if (e.getX() > 200 && e.getX() < 800 && e.getY() > 0 && e.getY() < 630) {
            var tile = this.getDeck().getTilePanel(x, y);

        }
    }
}
