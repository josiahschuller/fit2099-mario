package game.mechanics.world.positions.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.mechanics.combat.actors.Goomba;

public class Sprout extends Tree {

    /**
     * Sprout stage
     *
     */
    final static int SPROUT_START_AGE = 1;
    final static int SPROUT_END_AGE = 10;

    /**
     * Sprout event probabilities
     *
     */
    final static double SPAWN_GOOMBA_CHANCE = 0.1;

    /**
     * Constructor which initialises the display character and starting age
     *
     */
    public Sprout() {
        super('+', SPROUT_START_AGE);
        this.registerInstance(); // Register instance to be resettable
        this.setJumpSuccessRate(0.9);
        this.setFallDamage(10);
    }

    /**
     * Tick method, executed every turn
     * @param location - the location of the sprout
     */
    @Override
    public void tick(Location location) {
        // If the sprout has reached the end of its stage, convert it to a sapling
        if(super.getAge() == SPROUT_END_AGE){
            this.removeInstance(); // Remove instance from being resettable
            location.setGround(new Sapling());
        }
        // Attempt to spawn a goomba
        super.attemptToSpawn(location, new Goomba(), SPAWN_GOOMBA_CHANCE);
        
        // Execute the base tree tick method
        super.tick(location);
    }
}
