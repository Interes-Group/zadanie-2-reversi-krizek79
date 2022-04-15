package sk.stuba.fei.uim.oop.logic;

import sk.stuba.fei.uim.oop.deck.Tile;

import java.awt.*;
public class Bot {

    private final GameLogic gameLogic;
    private final Color botColor;

    public Bot(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.botColor = Color.BLACK;
    }

    public void makeRandomMove() {
        gameLogic.getDeck().findValidTiles(botColor);
        if (!gameLogic.getDeck().getValidTiles().isEmpty()) {
            Tile tile = gameLogic.getDeck().getRandomValidTile();
            if (gameLogic.isMoveValid(tile, botColor)) {
                gameLogic.makeMove(tile, botColor);
                gameLogic.getMenuPanel().getPlayerLabel().setForeground(Color.WHITE);
                gameLogic.getMenuPanel().getPlayerLabel().setText("White");

                gameLogic.getDeck().findValidTiles(Color.WHITE);
                if (gameLogic.getDeck().getValidTiles().isEmpty()) {
                    makeRandomMove();
                }
            }
        } else {
            gameLogic.endGame();
        }
    }
}
