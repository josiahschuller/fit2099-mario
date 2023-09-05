package game.mechanics.world.positions;

import game.mechanics.magical.items.HealthWater;

public class HealthFountain extends Fountain {
    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H');
        this.water = new HealthWater();
    }

    /**
     * Returns a String that says what type of water it is
     * @return String of type of water
     */
    @Override
    public String toString() {
        return "Health Fountain";
    }
}
