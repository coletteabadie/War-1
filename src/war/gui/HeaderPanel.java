package war.gui;

import war.WarModel;

import javax.swing.*;
import java.awt.*;

/**
 * A<strong>Assignment #10</strong><br>
 * Represents the content at the top of the window for the application.
 *
 * @author Walker Crouse
 */
public class HeaderPanel extends JPanel {
    public static final String MESSAGE_WELCOME = "This means war!";
    public static final String MESSAGE_TURN_OVER = "%s won that battle!";
    public static final String MESSAGE_GAME_OVER = "Game Over! %s is the winner!";
    private final WarModel model;
    private final JLabel message = new JLabel(MESSAGE_WELCOME, SwingConstants.CENTER);
    private final JLabel nameTag1 = new JLabel(), nameTag2 = new JLabel();

    /**
     * Creates a new header panel.
     */
    public HeaderPanel(WarModel model) {
        super(new BorderLayout());
        this.model = model;

        add(nameTag1, BorderLayout.WEST);
        add(message, BorderLayout.CENTER);
        add(nameTag2, BorderLayout.EAST);
    }

    /**
     * Sets the message displayed in the header.
     *
     * @param message to display
     * @param args    to format with
     */
    public void setMessage(String message, Object... args) {
        this.message.setText(String.format(message, args));
    }

    /**
     * Updates the name tags to reflect the player's actual names.
     */
    public void updateNameTags() {
        nameTag1.setText(model.getPlayer(true).getName());
        nameTag2.setText(model.getPlayer(false).getName());
    }
}
