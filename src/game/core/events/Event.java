package game.core.events;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Base class for Actions. These represent things that the actor can do.
 */
public abstract class Event {

	private Ground ground;
    private Item item;
    
    public Event(Ground ground){
        this.ground = ground;
        this.item = null;
    };
    
    public Event(Item item){
        this.item = item;
        this.ground = null;
    };
    
    /**
	 * Perform the Action.
	 *
	 * @param actor The actor targeted by the event.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	public abstract String execute(Actor actor);
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	public abstract String menuDescription(Actor actor);
}
