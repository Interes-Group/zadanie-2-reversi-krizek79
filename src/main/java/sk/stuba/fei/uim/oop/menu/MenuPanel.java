package sk.stuba.fei.uim.oop.menu;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class MenuPanel extends JPanel {


    private final GameSizeSlider gameSizeSlider;
    private final GameResetButton gameResetButton;
    private final JLabel gameSizeLabel;
    private final JLabel playerLabel;

    public MenuPanel() {
        setPreferredSize(new Dimension(200, 600));
        setLayout(new GridLayout(4, 1));
        setBackground(Color.GRAY);
        setFocusable(true);

        gameSizeSlider = new GameSizeSlider();

        gameResetButton = new GameResetButton();

        gameSizeLabel = new JLabel("Size: " + gameSizeSlider.getValue()
                + "x" + gameSizeSlider.getValue(), SwingConstants.CENTER);
        gameSizeLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
        gameSizeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        playerLabel = new JLabel("White", SwingConstants.CENTER);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(new Font("Verdana", Font.PLAIN, 22));
        playerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(gameSizeLabel);
        add(gameSizeSlider);
        add(gameResetButton);
        add(playerLabel);
    }

}
