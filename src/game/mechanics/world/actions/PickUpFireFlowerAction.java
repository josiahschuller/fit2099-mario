package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;
import game.mechanics.world.items.FireFlower;

public class PickUpFireFlowerAction extends PickUpItemAction {
    
    /**
     * Current item
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public PickUpFireFlowerAction(Item item) {
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
        map.locationOf(actor).removeItem(item);
        actor.addItemToInventory(item);
        actor.addCapability(Status.FIRE_ATTACK);

        return actor + " can use FIRE ATTACK!";
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
        FireFlower fireFlower = (FireFlower) item;
        return actor + " consumes a Power Star - " + fireFlower.getTurnsLeft() + " turns remaining";
    }

    
}
