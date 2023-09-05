package game.mechanics.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ResetAction extends Action {

    /**
     * Manager for resetting everything
     */
    private ResetManager manager;

    /**
     * Whether the ResetAction has been used or not
     */
    private static boolean used;

    /**
     * Constructor
     */
    public ResetAction() {
        this.manager = ResetManager.getInstance();
        this.used = false;
    }

    /**
     * Checks whether the ResetAction has been used or not
     * @return boolean of whether ResetAction has been used
     */
    public static boolean hasBeenUsed() {
        return used;
    }

    /**
     * Perform the ResetAction.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.used = true; // The ResetAction has been used now
        manager.run(); // Reset everything
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " resets the game";
    }

    /**
     * Returns the key used in the menu to trigger this ResetAction.
     *
     * There's no central management system for this, so you need to be careful not to use the same one twice.
     * See https://en.wikipedia.org/wiki/Connascence
     *
     * @return The key we use for this Action in the menu, or null to have it assigned for you.
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
