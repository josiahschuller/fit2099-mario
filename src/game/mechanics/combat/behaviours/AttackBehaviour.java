package game.mechanics.combat.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.core.behaviours.Behaviour;
import game.mechanics.combat.actors.Enemy;

public class AttackBehaviour implements Behaviour {

    
	private final Actor target;
	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to follow
	 */
	public AttackBehaviour(Actor subject) {
		this.target = subject;
	}

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Check if target is within attack distance
        if(distance(map.locationOf(actor), map.locationOf(target)) == 1){
            // If yes, determine attack direction and return attack action
            for (Exit exit : map.locationOf(actor).getExits()) {
                Actor otherActor = exit.getDestination().getActor();
                if(otherActor == target){
                    return ((Enemy) actor).getAttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }

    private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
