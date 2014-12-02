package war.gui;

import war.WarModel;

import javax.swing.*;

/**
 * Assignment #10
 * Represents a runnable that can simulate a game.
 *
 * @author Walker Crouse
 */
public class WarSimulator implements Runnable {
    private final WarModel model;
    private final WarGui gui;
    private boolean cancel = false;

    /**
     * Creates a new simulator.
     *
     * @param model to use
     * @param gui to use
     */
    public WarSimulator(WarModel model, WarGui gui) {
        this.model = model;
        this.gui = gui;
    }

    /**
     * Stops the simulator.
     */
    public void cancel() {
        cancel = true;
    }

    @Override
    public void run() {
        // start a game
        model.newGame();
        // press buttons until the game is over or cancelled
        while (!model.isGameOver() && !cancel) {
            ControlPanel controls = gui.getControls();
            JButton dBtn = controls.getDrawButton();
            JButton mBtn = controls.getMobilizeButton();
            if (dBtn.isEnabled())
                dBtn.doClick();
            else
                mBtn.doClick();
        }
    }
}
