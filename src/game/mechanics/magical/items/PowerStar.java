package game.mechanics.magical.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.core.items.Perishable;
import game.mechanics.magical.actions.DropPowerStarAction;
import game.mechanics.magical.actions.PickUpPowerStarAction;
import game.mechanics.trading.items.Tradable;

public class PowerStar extends Item implements Tradable, Perishable {

    /**
     * Value of the power start in dollars
     */
    private final int VALUE = 600;
    
    /**
     * Number of turns left until disappearing
     */
    private int turnsLeft;

    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        turnsLeft = 10;
    }

    /**
     * Decreases the number of turns left by 1.
     */
    @Override
    public void decreaseTurnsLeft() {
        this.turnsLeft -= 1;
    }

    /**
     * Gets the number of turns left before disappearing
     * @return number of turns left
     */
    @Override
    public int getTurnsLeft() {
        return this.turnsLeft;
    }

    /**
     * Sets the number of turns left before disappearing
     * @param turnsLeft new number of turns left
     */
    @Override
    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
    }

    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        decreaseTurnsLeft();
        if (getTurnsLeft() <= 0) {
            // If there are no turns left, then remove the Power Star
            currentLocation.removeItem(this);
        }
    }

    /**
     * Inform an Item in an actor's inventory of the passage of time.
     * This method is called once per turn.
     * @param currentLocation The location of the actor with the item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        decreaseTurnsLeft();
        if (getTurnsLeft() <= 0) {
            // If there are no turns left, then drop the Power Star
            this.getDropAction(actor).execute(actor, currentLocation.map());
        }
    }

    /**
     * Create and return an action to pick this Item up.
     * @return a new PickUpItemAction.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpPowerStarAction(this);
    }

    /**
     * Create and return an action to drop this Item.
     * @return a new DropItemAction.
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return new DropPowerStarAction(this);
    }

    @Override
    public int getValue() {
        return VALUE;
    }
}
