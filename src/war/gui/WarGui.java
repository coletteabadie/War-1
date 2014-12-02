package war.gui;

import war.Card;
import war.Player;
import war.WarModel;
import war.WarView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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

    private JMenuBar createMenuBar() {
        // create menu
        JMenuBar menuBar = new JMenuBar();

        // file menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        // "new game" item
        JMenuItem newGame = new JMenuItem("New game", KeyEvent.VK_N);
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        fileMenu.add(newGame);
        newGame.addActionListener(a -> model.newGame());

        return menuBar;
    }

    @Override
    public void run() {
        // create window
        JFrame frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add menu to frame
        frame.setJMenuBar(createMenuBar());

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
        controls.getDrawButton().setEnabled(true);
    }

    @Override
    public void onTurnStart(Card card1, Card card2) {
        if (!model.isWar()) {
            table.getPlayerPanel(true).getWarPanel().setVisible(false);
            table.getPlayerPanel(false).getWarPanel().setVisible(false);
        }

        table.getPlayerPanel(true).getBattlePanel().showCard(card1);
        table.getPlayerPanel(false).getBattlePanel().showCard(card2);

        controls.setPoolCount(model.poolCount());

        // remove deck image if player is on last card
        Player p1 = model.getPlayer(true), p2 = model.getPlayer(false);
        JLabel deck1 = table.getPlayerPanel(true).getDeck(), deck2 = table.getPlayerPanel(false).getDeck();
        deck1.setVisible(p1.hasCard());
        deck2.setVisible(p2.hasCard());
    }

    @Override
    public void onWarStart() {
        header.setMessage(FRAME_TITLE);
        controls.getDrawButton().setEnabled(false);
        controls.getMobilizeButton().setEnabled(true);
        controls.setPoolCount(model.poolCount());
        controls.setCardCount(model.getPlayer(true).cardsLeft(), model.getPlayer(false).cardsLeft());
    }

    private Card mobilized1, mobilized2;

    @Override
    public void onMobilize(Card card1, Card card2) {
        mobilized1 = card1;
        mobilized2 = card2;

        PlayerPanel p1 = table.getPlayerPanel(true), p2 = table.getPlayerPanel(false);
        p1.getBattlePanel().setVisible(false);
        p2.getBattlePanel().setVisible(false);

        p1.getWarPanel().showBack();
        p2.getWarPanel().showBack();

        controls.setPoolCount(model.poolCount());
        controls.getMobilizeButton().setEnabled(false);
        controls.getDrawButton().setEnabled(true);
    }

    @Override
    public void onWarEnd() {
        table.getPlayerPanel(true).getWarPanel().showCard(mobilized1);
        table.getPlayerPanel(false).getWarPanel().showCard(mobilized2);
    }

    @Override
    public void onTurnEnd(Card card1, Card card2, Player winner) {
        header.setMessage(HeaderPanel.MESSAGE_TURN_OVER, winner.getName());
        controls.setCardCount(model.getPlayer(true).cardsLeft(), model.getPlayer(false).cardsLeft());
    }

    @Override
    public void onGameOver(Player winner) {
        header.setMessage(HeaderPanel.MESSAGE_GAME_OVER, winner.getName());
        controls.getDrawButton().setEnabled(false);
        controls.setPoolCount(model.poolCount());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WarGui());
    }
}
