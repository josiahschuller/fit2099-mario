package game.mechanics.magical.items;

import game.mechanics.magical.actors.DrinkingActor;

public class HealthWater extends Water {

    /**
     * Amount of healing upon being drunk
     */
    final private int healAmount = 50;

    /**
     * This method applies the effect of the health water on the actor
     * @param actor the actor drinking the water
     */
    @Override
    public void effect(DrinkingActor actor) {
        actor.heal(this.healAmount); // Heal the actor
    }


    /**
     * Returns a String that says what type of water it is
     * @return String of type of water
     */
    @Override
    public String toString() {
        return "Health Water";
    }
}
