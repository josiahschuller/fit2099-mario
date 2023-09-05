package game.mechanics.world.misc;

import edu.monash.fit2099.engine.positions.Location;

public class TeleportManager {

    /**
     * Next location to teleport to
     */
    private Location targetLocation;

    /**
     * Static instance of this class
     */
    private static TeleportManager instance;

    /**
     * Constructor
     */
    private TeleportManager() {
    }

    /**
     * Gets the static instance of this class
     * @return TeleportManager instance
     */
    public static TeleportManager getInstance() {
        if(instance == null){
            instance = new TeleportManager();
        }
        return instance;
    }

    /**
     * Gets the next location to teleport to
     * @return target location
     */
    public Location getTargetLocation() {
        return this.targetLocation;
    }

    /**
     * Sets the next location to teleport to
     * @param targetLocation target location
     */
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }
}
