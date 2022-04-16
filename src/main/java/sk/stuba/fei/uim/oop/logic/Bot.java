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
        if (!gameLogic.noMovesAvailableForBothPlayers()) {
            gameLogic.getDeck().findValidTiles(botColor);
            if (!gameLogic.getDeck().getValidTiles().isEmpty()) {
                Tile tile = gameLogic.getDeck().getRandomValidTile();

                gameLogic.getMenuPanel().getPlayerLabel().setForeground(Color.BLACK);
                gameLogic.getMenuPanel().getPlayerLabel().setText("Black");
                gameLogic.makeMove(tile, botColor);

                gameLogic.getDeck().findValidTiles(Color.WHITE);
                gameLogic.getMenuPanel().getPlayerLabel().setForeground(Color.WHITE);
                gameLogic.getMenuPanel().getPlayerLabel().setText("White");

                if (gameLogic.getDeck().getValidTiles().isEmpty()) {
                    makeRandomMove();
                }
            }
        }
    }
}
