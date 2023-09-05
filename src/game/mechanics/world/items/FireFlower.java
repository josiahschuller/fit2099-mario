package game.mechanics.world.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.core.items.Perishable;
import game.mechanics.world.actions.DropFireFlowerAction;
import game.mechanics.world.actions.PickUpFireFlowerAction;

public class FireFlower extends Item implements Perishable {

    private int turnsLeft;

    public FireFlower() {
        super("Fire flower", 'f', true);
        this.setTurnsLeft(20);
    }

    @Override
    public void decreaseTurnsLeft() {
        turnsLeft --;
    }

    @Override
    public int getTurnsLeft() {
        return turnsLeft;
    }

    @Override
    public void setTurnsLeft(int turnsLeft) {
       this.turnsLeft = turnsLeft;   
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        decreaseTurnsLeft();
        if (getTurnsLeft() <= 0) {
            this.getDropAction(actor).execute(actor, currentLocation.map());
        }
    }

    /**
     * Create and return an action to pick this Item up.
     * @return a new PickUpItemAction.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpFireFlowerAction(this);
    }

    /**
     * Create and return an action to drop this Item.
     * @return a new DropItemAction.
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return (getTurnsLeft() <= 0) 
        ? new DropFireFlowerAction(this)
        : null;
    }
    
}
