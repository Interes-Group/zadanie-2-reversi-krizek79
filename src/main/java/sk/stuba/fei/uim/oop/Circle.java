package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
public class Circle {

    private final Tile tile;
    private final Integer xPos;
    private final Integer yPos;
    private final Integer size;

    @Setter
    private Color color;

    public Circle(Tile tile, Integer xPos, Integer yPos, Integer size, Color color) {
        this.tile = tile;
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xPos + 10, yPos + 10, size - 20, size - 20);
        g.setColor(Color.BLACK);
        g.drawOval(xPos + 10, yPos + 10, size - 20, size - 20);
    }

}
