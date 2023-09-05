package game.mechanics.combat.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.world.items.BowserFire;

public class BowserAttackAction extends AttackAction {
    
    /**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;


	   /**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
    public BowserAttackAction(Actor target, String direction) {
        super(target, direction);
		this.target = target;
		this.direction = direction;
    }

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		result += super.dealMeleeDamage(actor, map);
		result += super.dealFireDamage(actor, map, new BowserFire());
		result += this.attackOutcome(actor, map);
		return result;
	}
}
