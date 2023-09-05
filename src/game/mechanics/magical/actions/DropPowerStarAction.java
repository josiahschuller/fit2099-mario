package game.mechanics.magical.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;

public class DropPowerStarAction extends DropItemAction {

    /**
     * Current item
     */
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the item to drop
     */
    public DropPowerStarAction(Item item) {
        super(item);
        this.item = item;
    }

    /**
     * Drop the item.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);

        actor.removeCapability(Status.SUPER_WALK);
        actor.removeCapability(Status.IMMUNITY);
        actor.removeCapability(Status.INSTA_KILL);

        return "Power Star has run out";
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player loses the Super Mushroom and its effects"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drops Power Star";
    }
}

