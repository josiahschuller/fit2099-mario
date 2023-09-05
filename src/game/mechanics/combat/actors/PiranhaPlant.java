package game.mechanics.combat.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.mechanics.combat.actions.AttackAction;
import game.mechanics.reset.Resettable;

public class PiranhaPlant extends Enemy implements Resettable {

    /**
     * Constructor
    */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150, new IntrinsicWeapon(90, "Chomps"));
        // super("Piranha Plant", 'Y', 1);
        this.registerInstance();
    }

    /**
	 * Figure out what Piranah Plant will do next. Essentially, it'll just attack if possible, otherwise
     * it'll do nothing
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action action;
        super.createAttackBehaviour(map);
        action = super.actOnBehaviours(map);
		return action;
    }

    /**
	 * Configures Piranah Plant's attack action, which is a standard AttackAction
	 * @param target the Actor being attacked by Bowser
	 * @param direction  String representing the direction of the target
	 * @return an AttackAction target the target actor
	 */
    @Override
    public AttackAction getAttackAction(Actor target, String direction) {
        return new AttackAction(target, direction);
    }   

    /**
     * Called when game is reset. RESET status triggers reset behaviour in the play turn method
    */
    @Override
	public void resetInstance() {
		this.resetMaxHp(this.getMaxHp() + 50); // Increase max HP by 50 and heal to max
	}

}
