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
    private final WarModel model;
    private JButton actionBtn = new JButton(), newGameBtn = new JButton("New Game");
    private Runnable action;
    private JLabel cardCount1 = new JLabel("26"), cardCount2 = new JLabel("26");

    /**
     * Creates a new control panel.
     *
     * @param model to control
     * @param gui being used
     */
    public ControlPanel(WarModel model, WarGui gui) {
        super(new BorderLayout());
        this.model = model;
        setAction("Draw", model::nextTurn);

        // add stat counter
        JPanel stats = new JPanel(new BorderLayout());
        stats.add(cardCount1, BorderLayout.WEST);
        stats.add(cardCount2, BorderLayout.EAST);
        add(stats, BorderLayout.NORTH);

        // add buttons
        JPanel buttons = new JPanel(new BorderLayout());

        // add action button
        buttons.add(actionBtn, BorderLayout.CENTER);
        actionBtn.addActionListener(a -> action.run());

        // add "new game" button
        buttons.add(newGameBtn, BorderLayout.WEST);
        newGameBtn.addActionListener(a -> model.newGame());

        // add "auto-play" button
        JButton autoPlayBtn = new JButton("Auto-play");
        buttons.add(autoPlayBtn, BorderLayout.EAST);
        autoPlayBtn.addActionListener(a -> {
            if (gui.getActiveSimulator() == null) {
                gui.startSimulator();
                autoPlayBtn.setText("Cancel");
            } else {
                gui.stopSimulator();
                autoPlayBtn.setText("Auto-play");
            }
        });

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
     * Returns the action button.
     *
     * @return action button
     */
    public JButton getActionButton() {
        return actionBtn;
    }

    /**
     * Returns the "New Game" button.
     *
     * @return new game button
     */
    public JButton getNewGameBtn() {
        return newGameBtn;
    }

    /**
     * Sets the action to run when the button is clicked.
     *
     * @param name of action
     * @param action to run
     */
    public void setAction(String name, Runnable action) {
        actionBtn.setText(name);
        this.action = action;
    }
}
