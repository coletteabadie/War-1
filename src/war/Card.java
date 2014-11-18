package war;

/**
 * Assignment #10
 *
 * @author Walker Crouse
 */
public class Card implements Comparable<Card> {
    public static final int RANK_JACK = 11;
    public static final int RANK_QUEEN = 12;
    public static final int RANK_KING = 13;
    public static final int RANK_ACE = 14;

    private final int rank;
    private final Suit suit;
    private boolean faceUp = true;

    public Card(int rank, Suit suit) {
        if (rank < 2 || rank > RANK_ACE)
            throw new IllegalArgumentException("Card rank must be between 2 and 14 (RANK_ACE)");
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    @Override
    public int compareTo(Card card) {
        return rank - card.rank;
    }

    public static enum Suit {
        SPADES,
        CLUBS,
        HEARTS,
        DIAMONDS
    }
}
