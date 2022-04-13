package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

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
