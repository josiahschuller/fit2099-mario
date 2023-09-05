package game.mechanics.world.positions;

import game.mechanics.magical.items.PowerWater;

public class PowerFountain extends Fountain {
    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A');
        this.water = new PowerWater();
    }

    /**
     * Returns a String that says what type of water it is
     * @return String of type of water
     */
    @Override
    public String toString() {
        return "Power Fountain";
    }
}
