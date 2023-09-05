package game.mechanics.combat.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.world.items.FlowerFire;

public class FireFlowerAttack extends AttackAction {

    
	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

    public FireFlowerAttack(Actor target, String direction) {
        super(target, direction);
		this.target = target;
		this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Create a flower fire at the targets location and execute one tick to burn them
        String result = "";
        result += super.dealFireDamage(actor, map, new FlowerFire());
        result += super.attackOutcome(actor, map);
        return result;
    }

    @Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " with fire at " + direction;
	}
    
}
