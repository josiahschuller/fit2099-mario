package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.mechanics.world.misc.TeleportManager;

public class TeleportAction extends Action {

    /**
     * Manager that keeps track of teleport location
     */
    private TeleportManager manager;

    /**
     * Location to teleport to
     */
    private Location targetLocation;

    /**
     * Constructor
     */
    public TeleportAction() {
        this.manager = TeleportManager.getInstance();
        this.targetLocation = this.manager.getTargetLocation();
    }

    /**
     * Executes the TeleportAction
     * @param actor The actor performing the action.
     * @param currentMap The map the actor is on.
     * @return string to be printed to console upon execution
     */
    @Override
    public String execute(Actor actor, GameMap currentMap) {
        Location currentLocation = currentMap.locationOf(actor);
        currentMap.removeActor(actor);
        // Remove Piranha plant from other end of teleport
        if (this.targetLocation.containsAnActor()) {
            this.targetLocation.map().removeActor(this.targetLocation.getActor());
        }
        this.targetLocation.map().addActor(actor, this.targetLocation);
        this.manager.setTargetLocation(currentLocation);


        return actor + " teleported to another map";
    }

    /**
     * Menu description of the action
     * @param actor The actor performing the action.
     * @return string to be printed in the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to other map";
    }
}
