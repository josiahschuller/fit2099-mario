package game.mechanics.magical.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.mechanics.magical.actions.DropSuperMushroomAction;
import game.mechanics.magical.actions.PickUpSuperMushroomAction;
import game.mechanics.trading.items.Tradable;

public class SuperMushroom extends Item implements Tradable {
    
    /***
     * Value of the super mushroom in dollars.
     */
    private final int VALUE = 400;
    
    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
    }

    /**
     * Create and return an action to pick this Item up.
     * @return a new PickUpItemAction.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpSuperMushroomAction(this);
    }

    /**
     * Create and return an action to drop this Item.
     * @return a new DropItemAction.
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return new DropSuperMushroomAction(this);
    }

    @Override
    public int getValue() {
        return VALUE;
    }
}
