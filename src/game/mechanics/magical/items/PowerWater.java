package game.mechanics.magical.items;

import game.mechanics.magical.actors.DrinkingActor;


public class PowerWater extends Water {

    /**
     * Amount that base/intrinsic attack damage is increased by
     */
    final private int buffAmount = 15;

    /**
     * This method applies the effect of the power water on the actor
     * @param actor the actor drinking the water
     */
    @Override
    public void effect(DrinkingActor actor) {
        actor.increaseBaseDamage(buffAmount);
    }


    /**
     * Returns a String that says what type of water it is
     * @return String of type of water
     */
    @Override
    public String toString() {
        return "Power Water";
    }
}
