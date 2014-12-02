package war.gui;

import war.Card;
import war.Player;
import war.WarModel;
import war.WarView;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment #10
 * GUI Implementation of "War" card game.
 *
 * @author Walker Crouse
 */
public class WarGui extends JPanel implements WarView, Runnable {
    public static final String FRAME_TITLE = "War!";
    private final WarModel model = new WarModel(this);
    private final HeaderPanel header = new HeaderPanel();
    private final TablePanel table = new TablePanel();
    private final ControlPanel controls = new ControlPanel(model);

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
        controls.getDrawButton().setEnabled(true);
        controls.updateStats();

        // clear board
        PlayerPanel p1 = table.getPlayerPanel(true);
        p1.getBattlePanel().setVisible(false);
        p1.getWarPanel().setVisible(false);

        PlayerPanel p2 = table.getPlayerPanel(false);
        p2.getBattlePanel().setVisible(false);
        p2.getWarPanel().setVisible(false);

        // set welcome message
        header.setMessage(HeaderPanel.MESSAGE_WELCOME);
    }

    @Override
    public void onTurnStart(Card card1, Card card2) {
        // clear "war panel" if displayed
        if (!model.isWar()) {
            table.getPlayerPanel(true).getWarPanel().setVisible(false);
            table.getPlayerPanel(false).getWarPanel().setVisible(false);
        }

        // show drawn cards
        table.getPlayerPanel(true).getBattlePanel().showCard(card1);
        table.getPlayerPanel(false).getBattlePanel().showCard(card2);

        // remove deck image if player is on last card
        Player p1 = model.getPlayer(true), p2 = model.getPlayer(false);
        JLabel deck1 = table.getPlayerPanel(true).getDeck(), deck2 = table.getPlayerPanel(false).getDeck();
        deck1.setVisible(p1.hasCard());
        deck2.setVisible(p2.hasCard());
    }

    @Override
    public void onWarStart() {
        // set title
        header.setMessage(FRAME_TITLE);
        // disable draw button
        controls.getDrawButton().setEnabled(false);
        // enable mobilize button
        controls.getMobilizeButton().setEnabled(true);
        // update stats
        controls.updateStats();
    }

    private Card mobilized1, mobilized2;

    @Override
    public void onMobilize(Card card1, Card card2) {
        // save cards for later
        mobilized1 = card1;
        mobilized2 = card2;


        PlayerPanel p1 = table.getPlayerPanel(true), p2 = table.getPlayerPanel(false);
        p1.getBattlePanel().setVisible(false);
        p2.getBattlePanel().setVisible(false);

        p1.getWarPanel().showBack();
        p2.getWarPanel().showBack();

        controls.getMobilizeButton().setEnabled(false);
        controls.getDrawButton().setEnabled(true);
        controls.updateStats();
    }

    @Override
    public void onWarEnd() {
        table.getPlayerPanel(true).getWarPanel().showCard(mobilized1);
        table.getPlayerPanel(false).getWarPanel().showCard(mobilized2);
    }

    @Override
    public void onTurnEnd(Card card1, Card card2, Player winner) {
        header.setMessage(HeaderPanel.MESSAGE_TURN_OVER, winner.getName());
        controls.updateStats();
    }

    @Override
    public void onGameOver(Player winner) {
        header.setMessage(HeaderPanel.MESSAGE_GAME_OVER, winner.getName());
        controls.getDrawButton().setEnabled(false);
        controls.updateStats();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WarGui());
    }
}
