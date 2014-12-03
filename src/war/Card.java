package war;

import java.util.ArrayList;
import java.util.List;

/**
 * <strong>Assignment #10</strong><br>
 * Represents a playing card that is comparable to other cards. A card is considered greater than another card if it's
 * rank is greater than the other.
 *
 * @author Walker Crouse
 */
public class Card implements Comparable<Card> {
    // Ranks
    public static final int RANK_JACK = 11;
    public static final int RANK_QUEEN = 12;
    public static final int RANK_KING = 13;
    public static final int RANK_ACE = 14;

    public static final int RANK_FIRST = 2;
    public static final int RANK_LAST = RANK_ACE;

    public static final int STANDARD_DECK_SIZE = 52;

    protected final int rank;
    protected final Suit suit;
    protected boolean faceUp = true;

    /**
     * Constructs a new Card.
     *
     * @param rank of card (between 2 and 14 (inclusive))
     * @param suit of card
     */
    public Card(int rank, Suit suit) {
        if (rank < 2 || rank > RANK_ACE)
            throw new IllegalArgumentException("Card rank must be between 2 and 14 (RANK_ACE)");
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns a list of all possible unique {@link Card}s.
     *
     * @return list of all cards
     */
    public static List<Card> all() {
        List<Card> cards = new ArrayList<>(STANDARD_DECK_SIZE);
        for (int rank = RANK_FIRST; rank <= RANK_LAST; rank++) {
            for (Suit suit : Suit.values())
                cards.add(new Card(rank, suit));
        }
        return cards;
    }

    /**
     * Returns this card's rank.
     *
     * @return rank of card
     */
    public int getRank() {
        return rank;
    }

    /**
     * Returns this card's suit.
     *
     * @return card's suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns true if this card is face up.
     *
     * @return true if face up
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Sets if this card is face up or face down.
     *
     * @param faceUp true if should be face up
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    /**
     * Flips the card.
     */
    public void flip() {
        setFaceUp(!faceUp);
    }

    @Override
    public int compareTo(Card card) {
        return rank - card.rank;
    }

    @Override
    public String toString() {
        return "" + suit.toString().toLowerCase().charAt(0) + rank;
    }

    /**
     * Represents a playing card's suit.
     */
    public static enum Suit {
        SPADES,
        CLUBS,
        HEARTS,
        DIAMONDS
    }
}
