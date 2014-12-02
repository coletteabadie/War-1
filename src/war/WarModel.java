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

    public WarModel(WarView view) {
        this.view = view;
    }

    public void newGame() {
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

    public void nextTurn() {
        // players both have cards?
        if (!checkGameState())
            return;

        // draw cards
        Card card1 = player1.drawCard();
        Card card2 = player2.drawCard();

        System.out.println("Card 1 : " + card1);
        System.out.println("Card 2 : " + card2);

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

        winner.addCards(pool);
        pool.clear();

        if (war) {
            war = false;
            view.onWarEnd();
        }

        view.onTurnEnd(card1, card2, winner);
    }

    public void mobilize() {
        if (!war || !checkGameState())
            return;

        Card faceDown1 = player1.drawCard(), faceDown2 = player2.drawCard();
        faceDown1.setFaceUp(false);
        faceDown2.setFaceUp(false);

        pool.add(faceDown1);
        pool.add(faceDown2);

        view.onMobilize(faceDown1, faceDown2);
    }

    public void gameOver(Player winner) {
        gameOver = true;
        view.onGameOver(winner);
    }

    public int poolCount() {
        return pool.size();
    }

    public Player getPlayer(boolean p1) {
        return p1 ? player1 : player2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWar() {
        return war;
    }
}
