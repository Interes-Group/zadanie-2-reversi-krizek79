package sk.stuba.fei.uim.oop.deck;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class Stone {
    @Getter
    @Setter
    private Color color;

    public Stone(Color color) {
        this.color = color;
    }
}
