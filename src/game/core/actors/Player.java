package game.core.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.misc.Status;
import game.mechanics.magical.items.PowerStar;
import game.mechanics.magical.items.SuperMushroom;
import game.mechanics.reset.ResetAction;
import game.mechanics.reset.Resettable;
import game.mechanics.trading.actors.TradingActor;
import edu.monash.fit2099.engine.displays.Menu;

/**
 * Class representing the Player.
 */
public class Player extends TradingActor implements Resettable {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, new IntrinsicWeapon(5, "Punches"));
		this.addCapability(Status.IS_PLAYER);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.CAN_JUMP);
		this.registerInstance(); // Add Player to Reset Manager
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// Add reset action if it hasn't been used yet
		if (!ResetAction.hasBeenUsed()) {
			actions.add(new ResetAction());
		}

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * This method is called when the user uses a ResetAction and implements the functionality for being reset.
	 */
	public void resetInstance() {
		// Remove Super Mushroom status
		if (this.hasCapability(Status.TALL)) {
			this.removeCapability(Status.TALL);
			this.removeCapability(Status.JUMP_FREELY);
			this.removeItemFromInventory(new SuperMushroom()); // Remove Super Mushroom from inventory
		}
		// Remove Power Star status
		if (this.hasCapability(Status.SUPER_WALK)) {
			this.removeCapability(Status.SUPER_WALK);
			this.removeCapability(Status.IMMUNITY);
			this.removeCapability(Status.INSTA_KILL);
			this.removeItemFromInventory(new PowerStar()); // Remove Power Star from inventory
		}
		// Heal player to max
		this.heal(this.getMaxHp());
	}
}
