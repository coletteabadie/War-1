package war.gui;

import war.Card;
import war.Player;
import war.WarModel;
import war.WarView;

import javax.swing.*;
import java.awt.*;

/**
 * <strong>Assignment #10</strong><br>
 * GUI Implementation of "War" card game.
 *
 * @author Walker Crouse
 */
public class WarGui extends JPanel implements WarView, Runnable {
    public static final String FRAME_TITLE = "War!";
    private final WarModel model = new WarModel(this);
    private final HeaderPanel header = new HeaderPanel(model);
    private final TablePanel table = new TablePanel();
    private final ControlPanel controls = new ControlPanel(model, this);
    private WarSimulator sim;
    private Card mobilized1, mobilized2;

    /**
     * Creates and initializes the game.
     */
    public WarGui() {
        super(new BorderLayout());
        // add message
        add(header, BorderLayout.NORTH);
        // add playing table
        add(table, BorderLayout.CENTER);
        // add control panel
        add(controls, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WarGui());
    }

    /**
     * Starts a new game in auto-play mode.
     */
    public void startSimulator() {
        new Thread(sim = new WarSimulator(model, this)).start();
    }

    /**
     * Stops the auto-play.
     */
    public void stopSimulator() {
        sim.cancel();
        sim = null;
        controls.getAutoPlayButton().setText("Auto-play");
    }

    /**
     * Returns the simulator if one is currently running.
     *
     * @return simulator
     */
    public WarSimulator getActiveSimulator() {
        return sim;
    }

    /**
     * Returns the header panel.
     *
     * @return header panel
     */
    public HeaderPanel getHeader() {
        return header;
    }

    /**
     * Returns the table panel.
     *
     * @return table panel
     */
    public TablePanel getTable() {
        return table;
    }

    /**
     * Returns control panel.
     *
     * @return control panel
     */
    public ControlPanel getControls() {
        return controls;
    }

    @Override
    public void run() {
        // create window
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add content
        frame.setContentPane(this);

        // display window
        frame.pack();
        frame.setVisible(true);

        // automatically start a new game
        model.newGame();
    }

    @Override
    public void onGameStart() {
        // enable draw button and update stats
        controls.getActionButton().setEnabled(true);
        controls.updateStats();

        // clear board
        table.reset();

        // set welcome message
        header.setMessage(HeaderPanel.MESSAGE_WELCOME);
        header.updateNameTags();
    }

    @Override
    public void onTurnStart(Card card1, Card card2) {
        // clear "war panel" if displayed
        if (!model.isWar())
            table.reset();

        // show drawn cards
        table.showCards(card1, card2);

        // remove deck image if player is on last card
        table.getPlayerPanel(true).getDeck().setVisible(model.getPlayer(true).hasCard());
        table.getPlayerPanel(false).getDeck().setVisible(model.getPlayer(false).hasCard());
    }

    @Override
    public void onWarStart() {
        // set title
        header.setMessage(FRAME_TITLE);
        // set action to "mobilize"
        controls.setAction("Mobilize", model::mobilize);
        // update stats
        controls.updateStats();
    }

    @Override
    public void onMobilize(Card card1, Card card2) {
        // save cards for later
        mobilized1 = card1;
        mobilized2 = card2;

        // reset table
        table.reset();

        // show card back in "war" pane
        table.getPlayerPanel(true).getWarPanel().showBack();
        table.getPlayerPanel(false).getWarPanel().showBack();

        // disable mobilize button and enable draw button
        controls.setAction("Draw", model::nextTurn);
        controls.updateStats();
    }

    @Override
    public void onWarEnd() {
        // reveal hidden cards next time action button is clicked
        controls.setAction("Reveal", () -> {
            table.getPlayerPanel(true).getWarPanel().showCard(mobilized1);
            table.getPlayerPanel(false).getWarPanel().showCard(mobilized2);
            controls.setAction("Draw", model::nextTurn);
        });
    }

    @Override
    public void onTurnEnd(Card card1, Card card2, Player winner) {
        // set winner message
        header.setMessage(HeaderPanel.MESSAGE_TURN_OVER, winner.getName());
        controls.updateStats();
    }

    @Override
    public void onGameOver(Player winner) {
        header.setMessage(HeaderPanel.MESSAGE_GAME_OVER, winner.getName());
        controls.getActionButton().setEnabled(false);
        controls.updateStats();
    }
}
