package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Getter
public class TilePanel extends JPanel implements MouseListener {

    private final Integer tileSize;
    private final Integer xPos;
    private final Integer yPos;

    public TilePanel(Integer tileSize, Integer x, Integer y) {
        this.tileSize = tileSize;
        this.xPos = x;
        this.yPos = y;
        setSize(new Dimension(tileSize, tileSize));
        setBackground(Color.LIGHT_GRAY);

        Border border = BorderFactory.createLineBorder(Color.black);
        setBorder(border);

        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.YELLOW);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.LIGHT_GRAY);
    }
}
