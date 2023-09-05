package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.magical.items.Bottle;
import game.mechanics.magical.items.Water;

public class DrinkFromBottleAction extends Action {

    /**
     * Perform the DrinkFromBottleAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (Item item : actor.getInventory()) {
            if (item instanceof Bottle) {
                Bottle bottle = (Bottle) item;
                if (bottle.isEmpty()) {
                    return "Bottle is empty - nothing to drink!";
                }
                Water water = bottle.drink();
                return new DrinkAction(water).execute(actor, map);
            }
        }
        return null;
    }


    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from bottle";
    }
}
