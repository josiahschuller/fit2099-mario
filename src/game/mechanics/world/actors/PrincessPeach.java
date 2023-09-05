package game.mechanics.world.actors;

import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.core.behaviours.Behaviour;
import game.core.behaviours.FollowBehaviour;
import game.core.misc.Status;
import game.mechanics.combat.actors.Bowser;
import game.mechanics.reset.Resettable;
import game.mechanics.world.actions.CompleteGameAction;

public class PrincessPeach extends Actor implements Resettable {

    public static int PEACH_SPAWN_X = Bowser.BOWSER_SPAWN_X + 1;
    public static int PEACH_SPAWN_Y = Bowser.BOWSER_SPAWN_Y + 1;

    private static int FOLLOW_PRIORITY = 1;

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    public PrincessPeach() {
        super("Princess Peach", 'P', Integer.MAX_VALUE);
        this.addCapability(Status.IMMUNITY);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
		if(otherActor.hasCapability(Status.CAN_END_GAME)){
			actions.add(new CompleteGameAction());
		}
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // If game resets
		if (this.hasCapability(Status.RESET)) {
			this.removeInstance();
            map.removeActor(this);
            map.addActor(new PrincessPeach(), new Location(map, PEACH_SPAWN_X, PEACH_SPAWN_Y));
			return new DoNothingAction();
		} 
		// Normal turn
		else {
			// If the Goomba has no follow or attack behaviours ...
			if(this.behaviours.get(FOLLOW_PRIORITY) == null){
				// ... check if the player is next to the Goomba
				for (Exit exit : map.locationOf(this).getExits()) {
					Actor otherActor = exit.getDestination().getActor();
					// If the player is next to the Goomba, instantiate attack and follow behaviours
					if(otherActor instanceof Bowser){
						this.behaviours.put(FOLLOW_PRIORITY, new FollowBehaviour(otherActor));
					}
				}
			}
			// Iterate through behaviours by priority, and execute the highest priority one
			for(Behaviour Behaviour : behaviours.values()) {
				Action action = Behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
			return new DoNothingAction();
        }
    }

    @Override
	public void resetInstance() {
		this.addCapability(Status.RESET);
	}

    
}
