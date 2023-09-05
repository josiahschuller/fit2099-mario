package game.mechanics.world.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.core.misc.Status;
import game.mechanics.combat.actors.PiranhaPlant;
import game.mechanics.reset.Resettable;
import game.mechanics.world.actions.TeleportAction;

public class WarpPipe extends HigherGround implements Resettable {

    /**
     * Age of the Warp Pipe
     */
    private int age;

    /**
     * Constructor.
     */
    public WarpPipe() {
        super('C');
        age = 0;
        this.registerInstance();
    }

    /**
     * Gets the jump success rate
     *
     * @return jump success rate
     */
    @Override
    public double getJumpSuccessRate() {
        return 0.8;
    }

    /**
     * Gets the fall damage from a failed jump
     *
     * @return fall damage
     */
    @Override
    public int getFallDamage() {
        return 20;
    }

    /**
     * Spawns the Paranha Plant on the second turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age += 1;
        if (age == 1) {
            location.addActor(new PiranhaPlant());
        }

        // Respawn Piranha Plant when reset
        if (this.hasCapability(Status.RESET) && !location.containsAnActor()) {
            location.addActor(new PiranhaPlant());
        }
    }

    /**
     * Returns list of allowed actions, including a TeleportAction
     * @param actor actor to do the action
     * @param location location of actor
     * @param direction direction in which actor is to the Warp Pipe
     * @return list of allowed actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = super.allowableActions(actor, location, direction);

        // Check if actor is standing on Warp Pipe and is the player
        if (location.containsAnActor() && location.getActor() == actor && actor.hasCapability(Status.IS_PLAYER)) {
            list.add(new TeleportAction());
        }

        return list;
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESET);
    }
}
