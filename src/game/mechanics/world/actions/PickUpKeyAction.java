package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;

public class PickUpKeyAction extends PickUpItemAction {

    private Item item;

    public PickUpKeyAction(Item item) {
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
        String result = "";
        result += super.execute(actor, map);
        actor.addCapability(Status.CAN_END_GAME);
        result += actor + " can complete the game!";
        return result;
    }
    
}
