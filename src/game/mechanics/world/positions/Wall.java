package game.mechanics.world.positions;

import game.core.misc.Status;

public class Wall extends HigherGround {
	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
		this.addCapability(Status.STOMPABLE);
	}

	/**
	 * Gets the jump success rate
	 * 
	 * @return jump success rate
	 */
	@Override
	public double getJumpSuccessRate() {
		return 0.8;
	}

	/**
	 * Gets the fall damage from a failed jump
	 * 
	 * @return fall damage
	 */
	@Override
	public int getFallDamage() {
		return 20;
	}
}