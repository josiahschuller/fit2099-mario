package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.magical.items.Bottle;
import game.mechanics.world.positions.Fountain;

public class FillBottleAction extends Action {

    private Fountain fountain;


    public FillBottleAction(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Perform the FillBottleAction.
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
                bottle.fill(this.fountain.takeWater());
                return actor + " refills bottle from " + fountain.toString() + " (" + fountain.getCapacity() + "/" + fountain.getMaxCapacity() + ")";
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
        return actor + " fills bottle from " + this.fountain.toString();
    }
}
