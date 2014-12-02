package war.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class PlayerPanel extends JPanel {
    private final JLabel deck;
    private final CardPanel battlePanel, warPanel;

    public PlayerPanel(int horizontalAlignment) {
        super(new GridLayout(1, 3));
        setOpaque(false);

        add(deck = new JLabel(JCard.DEFAULT_BACK_IMAGE));
        add(battlePanel = new CardPanel(horizontalAlignment));
        add(warPanel = new CardPanel(horizontalAlignment));
    }

    public JLabel getDeck() {
        return deck;
    }

    public CardPanel getBattlePanel() {
        return battlePanel;
    }

    public CardPanel getWarPanel() {
        return warPanel;
    }
}
