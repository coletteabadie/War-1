package war;

/**
 * Assignment #10
 * Represents a visual representation of the War card game for a user to see.
 *
 * @author Walker Crouse
 */
public interface WarView {
    public void onGameStart();
    public void onTurnStart(Card card1, Card card2);
    public void onWarStart();
    public void onMobilize(Card card1, Card card2);
    public void onWarEnd();
    public void onTurnEnd(Card card1, Card card2, Player winner);
    public void onGameOver(Player winner);
}
