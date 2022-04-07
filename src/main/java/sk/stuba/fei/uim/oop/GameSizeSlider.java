package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class GameSizeSlider extends JSlider {

    public GameSizeSlider() {
        super(VERTICAL, 6, 12, 6);
        setMajorTickSpacing(2);
        setMinorTickSpacing(2);
        setSnapToTicks(true);
        setPaintTicks(true);
        setPaintLabels(true);
        setFocusable(false);
        setBackground(Color.GRAY);
    }
}
