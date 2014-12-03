package war;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * <strong>Assignment #10</strong><br>
 * Represents a participant in "War".
 *
 * @author Walker Crouse
 */
public class Player {
    private final LinkedList<Card> deck;
    private String name;

    /**
     * Creates a new player.
     *
     * @param name of player
     * @param deck cards player has
     */
    public Player(String name, LinkedList<Card> deck) {
        this.name = name;
        this.deck = deck;
    }

    /**
     * Returns true if the player has any cards left.
     *
     * @return true if has any cards left
     */
    public boolean hasCard() {
        return !deck.isEmpty();
    }

    /**
     * Returns the amount of cards this player has left.
     *
     * @return amount of cards player has
     */
    public int cardsLeft() {
        return deck.size();
    }

    /**
     * Removes the next card from this player's deck and returns it.
     *
     * @return card drawn
     */
    public Card drawCard() {
        return deck.pop();
    }

    /**
     * Adds a collection of cards to this player's deck.
     *
     * @param cards to add to deck
     */
    public void addCards(Collection<Card> cards) {
        deck.addAll(cards);
    }

    /**
     * Returns this player's name.
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this player's name
     *
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
