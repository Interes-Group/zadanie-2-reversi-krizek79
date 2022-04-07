package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class MenuPanel extends JPanel {

    private final GameSizeSlider gameSizeSlider;

    public MenuPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.GRAY);
        setFocusable(true);

        gameSizeSlider = new GameSizeSlider();
        add(gameSizeSlider);
    }

}
