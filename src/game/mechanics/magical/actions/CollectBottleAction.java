package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;
import game.mechanics.magical.items.Bottle;

public class CollectBottleAction extends Action {

    /**
     * Perform the CollectBottleAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle()); // Add bottle to inventory

        // Let the program know that the actor now has a bottle
        actor.addCapability(Status.HAS_BOTTLE);

        return actor + " collects a bottle from Toad";
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Collect a bottle from Toad";
    }
}
