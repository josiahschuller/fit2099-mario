package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.magical.actors.DrinkingActor;
import game.mechanics.magical.items.Water;

public class DrinkAction extends Action {

    private Water water;

    public DrinkAction(Water water) {
        this.water = water;
    }

    /**
     * Perform the DrinkAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        water.effect((DrinkingActor) actor);
        return this.menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks " + this.water.toString();
    }
}
