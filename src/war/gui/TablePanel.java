package war.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 * Represents the playing table for the game.
 *
 * @author Walker Crouse
 */
public class TablePanel extends JPanel {
    public static final Color BACKGROUND_COLOR = new Color(0, 84, 10);

    /**
     * Creates a new panel.
     */
    public TablePanel() {
        super(new GridBagLayout());
        setBackground(BACKGROUND_COLOR);

        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 200;

        PlayerPanel p1 = new PlayerPanel("Player 1");
        PlayerPanel p2 = new PlayerPanel("Player 2", true);

        add(p1, c);
        add(p2, c);

        p1.addDiscardChangesListener();
        p2.addDiscardChangesListener();
    }
}
