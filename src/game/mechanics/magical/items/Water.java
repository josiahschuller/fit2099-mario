package game.mechanics.magical.items;

import game.mechanics.magical.actors.DrinkingActor;

abstract public class Water {

    /**
     * This method applies the effect of the water on the actor
     * @param actor the actor drinking the water
     */
    abstract public void effect(DrinkingActor actor);

    /**
     * Returns a String that says what type of water it is
     * @return String of type of water
     */
    abstract public String toString();
}
