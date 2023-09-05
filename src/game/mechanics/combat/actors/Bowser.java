package game.mechanics.combat.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.misc.Status;
import game.mechanics.combat.actions.AttackAction;
import game.mechanics.combat.actions.BowserAttackAction;

import game.mechanics.reset.Resettable;
import game.mechanics.world.items.Key;

/**
 * The final boss who appears on the second map. Princess Peach follows
 * him when he moves. Bowser has a key, which will be dropped when his killed, 
 * which will allow the player to end the game.
*/
public class Bowser extends Enemy implements Resettable {

    public static int BOWSER_SPAWN_X = 10;
    public static int BOWSER_SPAWN_Y = 10;


    /**
     * Constructor. Bowser gets a key added to his inventory, which will be droppedwhen he's 
     * killed and can be used to end the game by the player
    */
    public Bowser() {
        super("Bowser", 'B', 500, new IntrinsicWeapon(80, "punches"));
        // super("Bowser", 'B', 1);
        this.addItemToInventory(new Key());
        this.registerInstance();
    }


    /**
	 * Figure out what Bowser will do next. Essentially, if game is being reset,
     * teleport bowser to spawn and heal. If it's a normal turn, Bowser will attempt
     * to execute follow and attack behaviours if the player is next to him. Otherwise
     * he'll do noting
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action action;
        // If game resets...
		if (this.hasCapability(Status.RESET)) {
            action = this.reset(map);
		} 
		// If normal turn ...
		else {
            super.createAttackBehaviour(map);
            super.createFollowBehaviour(map); 
            action = super.actOnBehaviours(map);
        }
        return action;
    }

    /**
	 * Configures Bowser's attack action, which is a BowserAttackAction
	 * @param target the Actor being attacked by Bowser
	 * @param direction  String representing the direction of the target
	 * @return an AttackAction target the target actor
	 */
    @Override
    public AttackAction getAttackAction(Actor target, String direction){
        return new BowserAttackAction(target, direction);
    }
    
    /**
     * Called when game is reset. RESET status triggers reset behaviour in the play turn method
    */
    @Override
	public void resetInstance() {
		this.addCapability(Status.RESET);
	}

    private Action reset(GameMap map){
         // ... remove Bowser from the reset manager
        this.removeInstance();
        // ... move him to his spawn point and restore health
        map.moveActor(this, new Location(map, BOWSER_SPAWN_X, BOWSER_SPAWN_Y));
        this.heal(this.getMaxHp());
        return new DoNothingAction();
    }
}
