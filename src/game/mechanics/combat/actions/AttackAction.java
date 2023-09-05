package game.mechanics.combat.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.core.misc.Status;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		String result = "";
		result += dealMeleeDamage(actor, map);
		result += attackOutcome(actor, map);
		return result;
	}

	protected String dealMeleeDamage(Actor actor, GameMap map){

		Weapon weapon = actor.getWeapon();
		String result;
		// If attacker misses attack, don't deal any damage
		if(!(rand.nextInt(100) <= weapon.chanceToHit())) {
			result = actor + " misses " + target + ".";
		}
		// If attacker has insta-kill, deal infinte damage
		else if (actor.hasCapability(Status.INSTA_KILL)) {
			int damage = Integer.MAX_VALUE;
			target.hurt(damage);
			result = actor + " insta-kills " + target + "!";
		} 
		// If target has immunity, don't deal any damage
		else if(target.hasCapability(Status.IMMUNITY)) {
			result = target + " is immune to attack!";
		}
		// Under all other conditions, deal the damage of the actors weapon
		else {
			int damage = weapon.damage();
			target.hurt(damage);
			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		}
		return result;
	};

	protected String dealFireDamage(Actor actor, GameMap map, Item fire){
		String result;
		if(target.hasCapability(Status.IMMUNITY)) {
			result = target + " is immune to attack!";
		}
		else{
			// Create a  fire at the targets location and execute one tick to burn them
			Location fireLocation = map.locationOf(target);
			fireLocation.addItem(fire);
			fire.tick(fireLocation);
			result =  actor + " attacks " + target + "at " + direction + "with fire!";
		}
		return result;
		
		
	}

	protected String attackOutcome(Actor actor, GameMap map){
		String result;
		// If target is killed during the attack, drop their inventory and remove them from the map
		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result = System.lineSeparator() + target + " is killed.";
		}
		// If target survives attack, don't do anything
		else{
			result = "";
		}
		return result;
	};

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
