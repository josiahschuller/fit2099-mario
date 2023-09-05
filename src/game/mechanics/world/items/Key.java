package game.mechanics.world.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.mechanics.world.actions.DropKeyAction;
import game.mechanics.world.actions.PickUpKeyAction;

public class Key extends Item {

    public Key() {
        super("Key", 'k', true);
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpKeyAction(this);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return new DropKeyAction(this);
    }
    
}
