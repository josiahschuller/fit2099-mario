package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;

public class PickUpSuperMushroomAction extends PickUpItemAction {

    /**
     * Current item
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public PickUpSuperMushroomAction(Item item) {
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

        actor.increaseMaxHp(50); // Increase max hp
        actor.addCapability(Status.TALL); // Make actor tall (uppercase display char)
        actor.addCapability(Status.JUMP_FREELY); // Make actor jump freely

        return menuDescription(actor);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Mario consumes a Super Mushroom!"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes a Super Mushroom!";
    }
}
