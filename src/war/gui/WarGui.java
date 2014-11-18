package war.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import war.Card;
import war.WarView;
import war.WarModel;

/**
 * Assignment #10
 * GUI Implementation of "War" card game.
 *
 * @author Walker Crouse
 */
public class WarGui extends JPanel implements WarView, Runnable {
    private static final String FRAME_TITLE = "War!";
    private static final Color BACKGROUND_COLOR = new Color(0, 84, 10);

    /**
     * Creates and initializes the game.
     */
    public WarGui() {
        setBackground(BACKGROUND_COLOR);
        JCard card = new JCard(2, Card.Suit.CLUBS);
        add(card.getComponent());

        JButton btn = new JButton("Flip");
        btn.addActionListener(e -> card.flip());
        add(btn);
    }

    @Override
    public void run() {
        // create window
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add content
        frame.add(this);

        // display window
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WarGui());
    }
}
