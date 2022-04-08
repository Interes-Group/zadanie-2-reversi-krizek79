package sk.stuba.fei.uim.oop;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

@Getter
public class MenuPanel extends JPanel {

    private final GameSizeSlider gameSizeSlider;
    private final JLabel gameSizeLabel;
    private final JLabel playerLabel;

    public MenuPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new GridLayout(3, 1));
        setBackground(Color.GRAY);
        setFocusable(true);

        gameSizeSlider = new GameSizeSlider();

        gameSizeLabel = new JLabel("Size: " + gameSizeSlider.getValue());
        gameSizeLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
        gameSizeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        playerLabel = new JLabel("Player: ");
        playerLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        playerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(gameSizeLabel);
        add(gameSizeSlider);
        add(playerLabel);
    }

}
