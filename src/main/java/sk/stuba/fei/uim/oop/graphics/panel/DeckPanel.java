package sk.stuba.fei.uim.oop.graphics.panel;

import javax.swing.*;
import java.awt.*;

public class DeckPanel extends JPanel {

    public DeckPanel() {
        setPreferredSize(new Dimension(600, 600));
        setLayout(new GridLayout());
        setFocusable(true);
        setBackground(Color.GREEN);
    }
}
