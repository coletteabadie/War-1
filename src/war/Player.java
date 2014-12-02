package war;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class Player {
    private final LinkedList<Card> deck;
    private String name;

    public Player(String name, LinkedList<Card> deck) {
        this.name = name;
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public boolean hasCard() {
        return !deck.isEmpty();
    }

    public int cardsLeft() {
        return deck.size();
    }

    public Card drawCard() {
        return deck.pop();
    }

    public void addCards(Collection<Card> cards) {
        deck.addAll(cards);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
