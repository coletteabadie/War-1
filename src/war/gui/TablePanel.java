package war.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class TablePanel extends JPanel {
    public static final Color BACKGROUND_COLOR = new Color(0, 102, 31);
    private final PlayerPanel player1, player2;

    public TablePanel() {
        super(new BorderLayout());
        setBackground(BACKGROUND_COLOR);
        add(player1 = new PlayerPanel(SwingConstants.LEFT), BorderLayout.WEST);
        add(player2 = new PlayerPanel(SwingConstants.RIGHT), BorderLayout.EAST);
    }

    public PlayerPanel getPlayerPanel(boolean p1) {
        return p1 ? player1 : player2;
    }
}
