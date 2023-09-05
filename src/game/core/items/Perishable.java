package game.core.items;

public interface Perishable {
    /**
     * Decreases the number of turns left by 1.
     */
    public void decreaseTurnsLeft();

    /**
     * Gets the number of turns left before disappearing
     * @return number of turns left
     */
    public int getTurnsLeft();

    /**
     * Sets the number of turns left before disappearing
     * @param turnsLeft new number of turns left
     */
    public void setTurnsLeft(int turnsLeft);
}
