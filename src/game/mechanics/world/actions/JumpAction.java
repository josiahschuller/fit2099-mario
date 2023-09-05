package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.core.misc.Status;

public class JumpAction extends Action {

    private Location moveToLocation;

    private String direction;

    private double successRate;

    private int fallDamage;

    public JumpAction(Location moveToLocation, String direction, double successRate, int fallDamage) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
        this.successRate = successRate;
        this.fallDamage = fallDamage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        double rand = Math.random();

        if (actor.hasCapability(Status.JUMP_FREELY) || rand < successRate) {
            map.moveActor(actor, moveToLocation);
            return this.menuDescription(actor);
        } else {
            actor.hurt(this.fallDamage);
            return actor + " fell and took " + this.fallDamage + " damage";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + this.direction;
    }
}
