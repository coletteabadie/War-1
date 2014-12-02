package war;

/**
 * <strong>Assignment #10</strong><br>
 * Represents a visual representation of the War card game for a user to see.
 *
 * @author Walker Crouse
 */
public interface WarView {
    /**
     * Called when the game starts.
     */
    public void onGameStart();

    /**
     * Called when a new turn starts.
     *
     * @param card1 card player 1 drew
     * @param card2 card player 2 drew
     */
    public void onTurnStart(Card card1, Card card2);

    /**
     * Called when "war" happens.
     */
    public void onWarStart();

    /**
     * Called when the players draw a card face down during war.
     *
     * @param card1 card player 1 drew
     * @param card2 card player 2 drew
     */
    public void onMobilize(Card card1, Card card2);

    /**
     * Called when war end.
     */
    public void onWarEnd();

    /**
     * Called when a turn ends.
     *
     * @param card1  card player 1 drew
     * @param card2  card player 2 drew
     * @param winner player that one the battle
     */
    public void onTurnEnd(Card card1, Card card2, Player winner);

    /**
     * Called when the game ends.
     *
     * @param winner player that did not run out of cards
     */
    public void onGameOver(Player winner);
}
