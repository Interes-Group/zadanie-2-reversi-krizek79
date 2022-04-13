package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class Stone {
    private Color color;

    public Stone(Color color) {
        this.color = color;
    }
}
