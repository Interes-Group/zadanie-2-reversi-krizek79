package sk.stuba.fei.uim.oop.utils;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.deck.Tile;

@Getter
@Setter
public class Connection {
    private Tile tile;
    private Boolean connected;

    public Connection(Tile tile) {
        this.tile = tile;
        this.connected = false;
    }
}
