package sk.stuba.fei.uim.oop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@AllArgsConstructor
public class Circle {

    private final Tile tile;
    private final Integer xPos;
    private final Integer yPos;
    private final Integer size;

    @Setter
    private Color color;

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(xPos + 10, yPos + 10, size - 20, size - 20);
        g.setColor(Color.BLACK);
        g.drawOval(xPos + 10, yPos + 10, size - 20, size - 20);
    }

}
