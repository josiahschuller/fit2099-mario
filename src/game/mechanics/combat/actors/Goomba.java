package game.mechanics.combat.actors;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.misc.Status;
import game.mechanics.reset.Resettable;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy implements Resettable {

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba",'g', 50, new IntrinsicWeapon(5, "Punches"));
		super.createwanderBehaviour();
		super.createSelfDestructBehaviour();
		this.registerInstance();
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// the action to be returned
		Action action;
		// if being reset, get reset action
		if(this.hasCapability(Status.RESET)){
			action = reset(map);
		}
		// If normal turn ...
		else{
			// ... create necessary behaviours
			super.createFollowBehaviour(map);
			super.createAttackBehaviour(map);
			super.createDrinkBehaviour(map);
			// act on behaviours
			action = actOnBehaviours(map);
		}
		return action;
	}

	/**
     * Called when game is reset. RESET status triggers reset behaviour in the play turn method
    */
	@Override
	public void resetInstance() {
		this.addCapability(Status.RESET);
	}

	private Action reset(GameMap map){
		// Remove Goomba from reset manager
		this.removeInstance(); 
		// Remove Goomba from game
		map.removeActor(this); 
		return new DoNothingAction();
	}

}
