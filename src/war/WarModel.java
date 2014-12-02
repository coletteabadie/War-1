package war;

import java.util.*;

/**
 * Assignment #10
 * Represents the actually progression and handling of a War game and it's rules.
 *
 * @author Walker Crouse
 */
public class WarModel {
    private final WarView view;
    private Player player1, player2;
    private List<Card> pool = new ArrayList<>();
    private boolean gameOver = true, war = false;

    /**
     * Constructs a new model.
     *
     * @param view to notify as game progresses
     */
    public WarModel(WarView view) {
        this.view = view;
    }

    /**
     * Clears current game and starts a new one.
     */
    public void newGame() {
        // reset variables
        gameOver = false;
        war = false;
        pool.clear();

        // create a standard deck of cards
        List<Card> deck = Card.all();
        // shuffle and split
        Collections.shuffle(deck);
        LinkedList<Card> deck1 = new LinkedList<>(deck.subList(0, deck.size() / 2));
        LinkedList<Card> deck2 = new LinkedList<>(deck.subList(deck.size() / 2, deck.size()));

        // create players
        player1 = new Player("Player 1", deck1);
        player2 = new Player("Player 2", deck2);

        // notify view
        view.onGameStart();
    }

    private boolean checkGameState() {
        // make sure both players can draw a card
        if (!player1.hasCard()) {
            gameOver(player2);
            return false;
        }
        if (!player2.hasCard()) {
            gameOver(player1);
            return false;
        }
        return true;
    }

    /**
     * Starts a new turn.
     */
    public void nextTurn() {
        // players both have cards?
        if (!checkGameState())
            return;

        // draw cards
        Card card1 = player1.drawCard();
        Card card2 = player2.drawCard();

        // add cards to pool
        pool.add(card1);
        pool.add(card2);

        // notify view
        view.onTurnStart(card1, card2);

        // compare cards
        int c = card1.compareTo(card2);
        Player winner;
        if (c > 0)
            // p1 wins
            winner = player1;
        else if (c < 0)
            // p2 wins
            winner = player2;
        else {
            // war!
            // make sure players can play
            if (!checkGameState())
                return;
            view.onWarStart();
            war = true;
            return;
        }

        // award pool to winner
        winner.addCards(pool);
        pool.clear();

        // end war if in progress
        if (war) {
            war = false;
            view.onWarEnd();
        }

        // notify view
        view.onTurnEnd(card1, card2, winner);
    }

    /**
     * Flips a card face down for each player to prepare for war.
     */
    public void mobilize() {
        // make sure war is in progress and both players have a card
        if (!war || !checkGameState())
            return;

        // draw two cards and add them to the pool
        Card faceDown1 = player1.drawCard(), faceDown2 = player2.drawCard();
        faceDown1.setFaceUp(false);
        faceDown2.setFaceUp(false);

        pool.add(faceDown1);
        pool.add(faceDown2);

        // notify view
        view.onMobilize(faceDown1, faceDown2);
    }

    /**
     * Called when one player runs out of cards.
     *
     * @param winner player that did not run out of cards
     */
    public void gameOver(Player winner) {
        gameOver = true;
        view.onGameOver(winner);
    }

    /**
     * Returns the amount of cards in the pool.
     *
     * @return amount of cards in the pool
     */
    public int poolCount() {
        return pool.size();
    }

    /**
     * Returns player 1 if p1 is true, player 2 otherwise.
     *
     * @param p1 true if should return player 1
     * @return player
     */
    public Player getPlayer(boolean p1) {
        return p1 ? player1 : player2;
    }

    /**
     * Returns true if the game is over.
     *
     * @return true if game is over
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns true if war is currently in progress.
     *
     * @return true if wartime
     */
    public boolean isWar() {
        return war;
    }
}
