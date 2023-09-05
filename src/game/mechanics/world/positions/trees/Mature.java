package game.mechanics.world.positions.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.mechanics.combat.actors.FlyingKoopa;
import game.mechanics.combat.actors.Koopa;

public class Mature extends Tree {

     /**
     * Mature stage
     *
     */
    final static int MATURE_START_AGE = 21;
    final static int SPREAD_FREQUENCY = 5;

    /**
     * Mature event probabilities
     *
     */
    final static double WITHER_CHANCE = 0.2;
    final static double SPAWN_KOOPA_CHANCE = 0.2;
    final static double FLYING_KOOPA_CHANCE = 0.5;

    /**
     * Constructor which initialises the display character and starting age
     *
     */
    public Mature() {
        super('T', MATURE_START_AGE);
        this.registerInstance(); // Register instance to be resettable
        this.setJumpSuccessRate(0.7);
        this.setFallDamage(30);
    }

    /**
     * Tick method, executed every turn
     * @param location - the location of the sapling
     */
    @Override
    public void tick(Location location) {
        // Attempt to spread to a nearby location
        if (super.getAge() % SPREAD_FREQUENCY == 0) {
            super.attemptToSpread(location);
        }
        // Attempt to spawn a Koopa
        if(super.simulateRandomEvent(FLYING_KOOPA_CHANCE)){
            super.attemptToSpawn(location, new FlyingKoopa(), SPAWN_KOOPA_CHANCE);
        }
        else{
            super.attemptToSpawn(location, new Koopa(), SPAWN_KOOPA_CHANCE);
        }
       
        // Attempt to wither away
        super.attemptToWither(location, WITHER_CHANCE);
        // Execute the base tree tick method
        super.tick(location);
    }
}
