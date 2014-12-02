package war.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class HeaderPanel extends JPanel {
    public static final String MESSAGE_WELCOME = "This means war!";
    public static final String MESSAGE_TURN_OVER = "%s won that battle!";
    public static final String MESSAGE_GAME_OVER = "Game Over! %s is the winner!";
    private final JLabel message = new JLabel(MESSAGE_WELCOME, SwingConstants.CENTER);

    public HeaderPanel() {
        super(new BorderLayout());
        add(message, BorderLayout.NORTH);
    }

    public void setMessage(String message, Object... args) {
        this.message.setText(String.format(message, args));
    }
}
