package game.mechanics.world.positions.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.mechanics.trading.items.Coin;

public class Sapling extends Tree {

     /**
     * Sapling stage
     *
     */
    final static int SAPLING_START_AGE = 11;
    final static int SAPLING_END_AGE = 20;
    
    /**
     * Sapling event probabilities
     *
     */
    final static double DROP_COIN_CHANCE = 0.1;
    final int DROPPED_COIN_VALUE = 20;

    /**
     * Constructor which initialises the display character and starting age
     *
     */
    public Sapling() {
        super('t', SAPLING_START_AGE);
        this.registerInstance(); // Register instance to be resettable
        this.setJumpSuccessRate(0.8);
        this.setFallDamage(20);
    }

    /**
     * Tick method, executed every turn
     * @param location - the location of the sapling
     */
    @Override
    public void tick(Location location) {
         // If the sapling has reached the end of its stage, convert it to a mature tree
        if(super.getAge() == SAPLING_END_AGE){
            this.removeInstance(); // Remove instance from being resettable
            location.setGround(new Mature());
        }
        // Attempt to drop a coin at the sprouts location
        super.attemptToDrop(location, new Coin(DROPPED_COIN_VALUE), DROP_COIN_CHANCE);
        // Execute the base tree tick method
        super.tick(location);
    }
}
