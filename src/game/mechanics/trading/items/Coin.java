package game.mechanics.trading.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.core.misc.Status;
import game.mechanics.reset.Resettable;
import game.mechanics.trading.actions.PickUpCoinAction;

public class Coin extends Item implements Resettable {

    private int value;

    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        this.registerInstance(); // Add Coin to Reset Manager
    }
    
    public int getValue(){
        return value;
    }

    /**
     * Create and return an action to pick this Item up.
     * @return a new PickUpItemAction.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpCoinAction(this);
    }

    /**
     * Inform a Coin on the ground of the passage of time.
     * This method is called once per turn, if the Coin rests upon the ground.
     * @param currentLocation The location of the ground on which the Coin lies.
     */
    @Override
    public void tick(Location currentLocation) {
        // If Coin is to be reset, then remove it
        if (this.hasCapability(Status.RESET)) {
            this.removeInstance(); // Remove Coin from Reset Manager
            currentLocation.removeItem(this); // Remove Coin from game
        }
    }

    /**
     * This method is called when the user uses a ResetAction and implements the functionality for being reset.
     */
    public void resetInstance() {
        this.addCapability(Status.RESET); // Mark Coin to be reset
    }

}
