package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;
import game.mechanics.magical.items.PowerStar;

public class PickUpPowerStarAction extends PickUpItemAction {

    /**
     * Current item
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public PickUpPowerStarAction(Item item) {
        super(item);
        this.item = item;
    }

    /**
     * Add the item to the actor's inventory.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        PowerStar star = (PowerStar) item;
        star.setTurnsLeft(10); // Reset turns left to 10 upon pick up

        map.locationOf(actor).removeItem(item);
        actor.addItemToInventory(item);

        actor.heal(200); // Heal 200 hit points
        actor.addCapability(Status.SUPER_WALK);
        actor.addCapability(Status.IMMUNITY);
        actor.addCapability(Status.INSTA_KILL);

        return actor + " is INVINCIBLE!";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Mario consumes a Power Star!"
     */
    @Override
    public String menuDescription(Actor actor) {
        PowerStar star = (PowerStar) item;
        return actor + " consumes a Power Star - " + star.getTurnsLeft() + " turns remaining";
    }
}
