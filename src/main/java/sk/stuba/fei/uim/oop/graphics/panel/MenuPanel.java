package sk.stuba.fei.uim.oop.graphics.panel;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setFocusable(true);
        setBackground(Color.GRAY);
    }

}
