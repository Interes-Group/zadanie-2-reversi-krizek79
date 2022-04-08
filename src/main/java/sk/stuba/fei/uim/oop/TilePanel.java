package sk.stuba.fei.uim.oop;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
public class TilePanel extends JPanel {

    private final Integer tileSize;
    private final Integer xPos;
    private final Integer yPos;

    @Setter
    private Color color;

    public TilePanel(Integer tileSize, Integer x, Integer y) {
        this.tileSize = tileSize;
        this.xPos = x;
        this.yPos = y;
        this.color = Color.PINK;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, tileSize, tileSize);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, tileSize, tileSize);
    }
}
