package sk.stuba.fei.uim.oop.bot;

import lombok.Getter;
import sk.stuba.fei.uim.oop.deck.Tile;
import sk.stuba.fei.uim.oop.logic.GameLogic;

import java.awt.*;

@Getter
public class Bot {

    private final GameLogic gameLogic;
    private final Color botColor;

    public Bot(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.botColor = Color.BLACK;
    }

    public void makeRandomMove() {
        gameLogic.getDeck().findValidTiles(botColor);
        Tile tile = gameLogic.getDeck().getRandomValidTile();
        if (gameLogic.isMoveValid(tile, botColor)) {
            gameLogic.makeMove(tile, botColor);
            gameLogic.getMenuPanel().getPlayerLabel().setText("Player: White");
            gameLogic.getDeck().findValidTiles(Color.WHITE);
        }
    }
}
