package game.mechanics.world.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.core.misc.Status;
import game.mechanics.world.actions.JumpAction;
import game.mechanics.world.actions.StompAction;

abstract public class HigherGround extends Ground{

    /**
     * The success rate for attempting a jump to this ground
     */
    protected double jumpSuccessRate;

    /**
     * The fall damage taken after failing a jump to this ground
     */
    protected int fallDamage;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HigherGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Checks whether the actor can walk here or not
     *
     * @param actor the Actor to check
     * @return boolean of whether actor can walk here
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Says whether objects are blocked upon thrown or not
     *
     * @return boolean of whether objects are blocked
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * Gets the jump success rate
     *
     * @return jump success rate
     */
    public double getJumpSuccessRate() {
        return this.jumpSuccessRate;
    };

    /**
     * Gets the fall damage from a failed jump
     *
     * @return fall damage
     */
    public int getFallDamage() {
        return this.fallDamage;
    }

    /**
     * Sets the jump success rate
     * @param jumpSuccessRate the jump success rate
     */
    protected void setJumpSuccessRate(double jumpSuccessRate) {
        this.jumpSuccessRate = jumpSuccessRate;
    }

    /**
     * Sets the fall damage
     * @param fallDamage the fall damage
     */
    protected void setFallDamage(int fallDamage) {
        this.fallDamage = fallDamage;
    }

    /**
     * Gets the allowable actions on a HigherGround
     *
     * @return action list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList list = super.allowableActions(actor, location, direction);
        if (!location.containsAnActor() && actor.hasCapability(Status.CAN_JUMP)) {
            if (location.getGround().hasCapability(Status.STOMPABLE) && actor.hasCapability(Status.SUPER_WALK)) {
                list.add(new StompAction(location, direction));
            } else {
                list.add(new JumpAction(location, direction, this.getJumpSuccessRate(), this.getFallDamage()));
            }
        }
        return list;
    }
}
