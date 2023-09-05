package game.mechanics.world.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.core.misc.Status;
import game.mechanics.world.events.BurnEvent;
import game.mechanics.world.items.Burning;

public class Lava extends Ground implements Burning {

    /**
     * Amount of damage the lava hurts per turn
     */
    private final int BURN_DAMAGE = 15;

    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }

    /**
     * Implements functionality that is executed every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            BurnEvent burnEvent = new BurnEvent(this);
            burnEvent.execute(location.getActor());
        }
    }

    /**
     * Gets the burn damage of this lava
     * @return amount of damage
     */
    @Override
    public int getBurnDamage() {
        return this.BURN_DAMAGE;
    }

    /**
     * Returns whether the actor can enter this location or not
     * @param actor the Actor to check
     * @return whether the actor can enter
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.IS_PLAYER);
    }
}
