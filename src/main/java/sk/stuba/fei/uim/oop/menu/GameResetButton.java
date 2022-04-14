package sk.stuba.fei.uim.oop.menu;

import javax.swing.*;
import java.awt.*;

public class GameResetButton extends JButton {

    public GameResetButton() {
        setText("RESET");
        setFont(new Font("Verdana", Font.PLAIN, 24));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.LIGHT_GRAY);
        setFocusable(false);
    }
}
