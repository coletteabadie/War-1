package war.gui;

import war.WarModel;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 * Represents the panel with the "controls" on it.
 *
 * @author Walker Crouse
 */
public class ControlPanel extends JPanel {
    private JButton drawBtn = new JButton("Draw"), mobilizeButton = new JButton("Mobilize");
    private JLabel cardCount1 = new JLabel("26"), cardCount2 = new JLabel("26");
    private final WarModel model;

    /**
     * Creates a new control panel.
     *
     * @param model to control
     */
    public ControlPanel(WarModel model) {
        super(new BorderLayout());
        this.model = model;

        // add stat counter
        JPanel stats = new JPanel(new BorderLayout());
        stats.add(cardCount1, BorderLayout.WEST);
        stats.add(cardCount2, BorderLayout.EAST);
        add(stats, BorderLayout.NORTH);

        // add buttons
        JPanel buttons = new JPanel(new BorderLayout());

        // add "draw" button
        buttons.add(drawBtn, BorderLayout.NORTH);
        drawBtn.addActionListener(a -> model.nextTurn());

        // add "mobilize" button
        mobilizeButton.setEnabled(false);
        buttons.add(mobilizeButton, BorderLayout.CENTER);
        mobilizeButton.addActionListener(a -> model.mobilize());

        // add "new game" button
        JButton newGameBtn = new JButton("New Game");
        buttons.add(newGameBtn, BorderLayout.SOUTH);
        newGameBtn.addActionListener(a -> model.newGame());

        add(buttons, BorderLayout.CENTER);
    }

    /**
     * Synchronizes the card counts from the model with the GUI.
     */
    public void updateStats() {
        cardCount1.setText("" + model.getPlayer(true).cardsLeft());
        cardCount2.setText("" + model.getPlayer(false).cardsLeft());
    }

    /**
     * Returns the "Draw" button.
     *
     * @return draw button
     */
    public JButton getDrawButton() {
        return drawBtn;
    }

    /**
     * Returns the "Mobilize" button.
     *
     * @return mobilize button
     */
    public JButton getMobilizeButton() {
        return mobilizeButton;
    }
}
