package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;

public class DropKeyAction extends DropItemAction {

    private Item item;

    public DropKeyAction(Item item) {
        super(item);
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += super.execute(actor, map);
        actor.removeCapability(Status.CAN_END_GAME);
        result += actor + " cannot end the game!";
        return result;
    }
    
}
