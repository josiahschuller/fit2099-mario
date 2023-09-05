package game.mechanics.combat.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.misc.Status;
import game.mechanics.combat.items.KoopaShell;
import game.mechanics.reset.Resettable;

/* An enemy, which once disabled can be destroyed with a wrench to drop a super mushroom*/
public class Koopa extends Enemy implements Resettable  {

	/**
     * Constructor
    */
	public Koopa(){
		super("Koopa", 'k', 100, new IntrinsicWeapon(5, "Punches"));
		this.addItemToInventory(new KoopaShell());
		super.createwanderBehaviour();
		this.registerInstance();
	}

	/**
     * A protected constructor which can be used by child classes which need
	 * to extend Koopa behaviour (e.g. Flying Koopa)
    */
	protected Koopa(String name, char displayChar, int hitPoints){
		super(name, displayChar, hitPoints,  new IntrinsicWeapon(5, "Punches"));
		this.addItemToInventory(new KoopaShell());
		super.createwanderBehaviour();
		this.registerInstance();
	}


	/**
	 * Figure out what Koopa will do next. Essentially, if game is being reset,
     * the koopa will be removed. If it's a normal turn, Koopa  will attempt
     * to execute follow and attack behaviours if the player is next to him. If not,
	 * it'll attempt to drink from a fountain. Otherwise it will wander.
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