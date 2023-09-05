package game.mechanics.world.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.mechanics.magical.actions.FillBottleAction;
import game.mechanics.magical.items.Bottle;
import game.mechanics.magical.items.Water;

abstract public class Fountain extends Ground {

    /**
     * Amount of water in fountain
     */
    private int capacity;

    /**
     * Maximum capacity of fountain
     */
    final private int maxCapacity = 10;

    /**
     * Amount of turns it takes to reset fountain
     */
    final private int resetTime = 5;

    /**
     * Number of turns until fountain is active again
     */
    private int cooldown;

    /**
     * Whether an actor can drink from the fountain or not
     */
    private boolean active;

    /**
     * Water contained in fountain
     */
    protected Water water;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    protected Fountain(char displayChar) {
        super(displayChar);
        this.capacity = this.maxCapacity;
        this.cooldown = this.resetTime;
        this.active = true;
    }

    /**
     * Gets how much water is in the fountain
     * @return capacity of fountain
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Gets the maximum amount of water that can be contained in the fountain
     * @return max capacity of water in fountain
     */
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    /**
     * Gets whether the fountain is active or not (i.e. ready to be drunk from)
     * @return whether fountain is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * This method implements functionality that is ran every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (this.capacity <= 0) {
            this.active = false;
            this.capacity = this.maxCapacity;
        }
        if (!active) {
            if (this.cooldown == 0) {
                this.active = true;
                this.cooldown = this.resetTime;
            } else {
                this.cooldown -= 1;
            }
        }
    }

    public Water takeWater() {
        capacity -= 1;
        return this.water;
    }

    /**
     * Returns an Action list of the actions that can be done.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();

        // Check if fountain is active
        if (this.active) {
            // Check if actor has a bottle
            for (Item item : actor.getInventory()) {
                if (item instanceof Bottle) {
                    actionList.add(new FillBottleAction(this));
                    return actionList;
                }
            }
        }

        return actionList;
    }

    /**
     * Returns a String that says what type of water it is
     * @return String of type of water
     */
    abstract public String toString();
}
