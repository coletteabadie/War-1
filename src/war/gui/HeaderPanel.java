package war.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 * Represents the content at the top of the window for the application.
 *
 * @author Walker Crouse
 */
public class HeaderPanel extends JPanel {
    public static final String MESSAGE_WELCOME = "This means war!";
    public static final String MESSAGE_TURN_OVER = "%s won that battle!";
    public static final String MESSAGE_GAME_OVER = "Game Over! %s is the winner!";
    private final JLabel message = new JLabel(MESSAGE_WELCOME, SwingConstants.CENTER);

    /**
     * Creates a new header panel.
     */
    public HeaderPanel() {
        super(new BorderLayout());
        add(message, BorderLayout.NORTH);
    }

    /**
     * Sets the message displayed in the header.
     *
     * @param message to display
     * @param args to format with
     */
    public void setMessage(String message, Object... args) {
        this.message.setText(String.format(message, args));
    }
}
