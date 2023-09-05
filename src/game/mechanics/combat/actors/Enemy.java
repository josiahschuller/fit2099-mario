package game.mechanics.combat.actors;

import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.actors.Player;
import game.core.behaviours.Behaviour;
import game.core.behaviours.FollowBehaviour;
import game.core.behaviours.SelfDestructBehaviour;
import game.core.behaviours.WanderBehaviour;
import game.core.misc.Status;
import game.mechanics.combat.actions.AttackAction;
import game.mechanics.combat.actions.FireFlowerAttack;
import game.mechanics.combat.behaviours.AttackBehaviour;
import game.mechanics.magical.actors.DrinkingActor;
import game.mechanics.magical.behaviours.DrinkBehaviour;
import game.mechanics.world.positions.Fountain;

public abstract class Enemy extends DrinkingActor {

    private static int SELF_DESTRUCT_PRIORITY = 1;
	private static int ATTACK_PRIORITY = 2;
    private static int FOLLOW_PRIORITY = 3;
	private static int DRINK_PRIORITY = 4;
	private static int WANDER_PRIORITY = 5;

    /**
     * Behaviours are stored here based on priority
    */
	private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    public Enemy(String name, char displayChar, int hitPoints, IntrinsicWeapon intrinsicWeapon) {        
        super(name, displayChar, hitPoints, intrinsicWeapon);
    }
    
    /**
	 * Allow player to attack the Goomba
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList actions = new ActionList();
		// If the actor next to Bowser is HOSTILE (i.e. Player) ...
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			// ... configure an attack action for that actor
            actions.add(new AttackAction(this,direction));
            // If the actor also has a FIRE ATTACK capability ...
            if(otherActor.hasCapability(Status.FIRE_ATTACK)) {
                // ... configure a fire attack for the actor
                actions.add(new FireFlowerAttack(this,direction));
            }
		}
		return actions;
	}

    protected Action actOnBehaviours(GameMap map){
        // Iterate through behaviours by priority, and execute the highest priority one
        for(Behaviour Behaviour : behaviours.values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    protected void createSelfDestructBehaviour(){
        behaviours.put(SELF_DESTRUCT_PRIORITY, new SelfDestructBehaviour());
    }

    protected void createwanderBehaviour(){
        behaviours.put(WANDER_PRIORITY, new WanderBehaviour());
    }

    protected void createDrinkBehaviour(GameMap map){
        // If the Goomba has no drink behaviour ...
        if (this.behaviours.get(DRINK_PRIORITY) == null) {
            // ... check if Koopa is standing on a fountain
            if (map.locationOf(this).getGround() instanceof Fountain) {
                Fountain fountain = (Fountain) map.locationOf(this).getGround();
                // Check if fountain is active
                if (fountain.isActive()) {
                    this.behaviours.put(DRINK_PRIORITY, new DrinkBehaviour());
                }
            }
        }
    }

    protected void createFollowBehaviour(GameMap map){
         // ... Check if Goomba has no follow or attack behaviours ...
         if(this.behaviours.get(ATTACK_PRIORITY) == null || this.behaviours.get(FOLLOW_PRIORITY) == null){
            // if the player is next to the Goomba ...
            for (Exit exit : map.locationOf(this).getExits()) {
                Actor otherActor = exit.getDestination().getActor();
                // ... instantiate attack and follow behaviours
                if(otherActor instanceof Player){
                    this.behaviours.put(FOLLOW_PRIORITY, new FollowBehaviour(otherActor));
                    
                }
            }
        }
    }

    protected void createAttackBehaviour(GameMap map){
        // ... Check if Goomba has no follow or attack behaviours ...
        if(this.behaviours.get(ATTACK_PRIORITY) == null || this.behaviours.get(FOLLOW_PRIORITY) == null){
            // if the player is next to the Goomba ...
            for (Exit exit : map.locationOf(this).getExits()) {
                Actor otherActor = exit.getDestination().getActor();
                // ... instantiate attack and follow behaviours
                if(otherActor instanceof Player){
                    this.behaviours.put(ATTACK_PRIORITY, new AttackBehaviour(otherActor));
                }
            }
        }
   }

   // @Override
    // protected IntrinsicWeapon getIntrinsicWeapon() {
    //     return new IntrinsicWeapon(30, "punches");
    // }

	/**
	 * Configures Koopa's attack action, which is a standard AttackAction
	 * @param target the Actor being attacked by Koopa
	 * @param direction  String representing the direction of the target
	 * @return an AttackAction target the target actor
	 */
	public AttackAction getAttackAction(Actor target, String direction) {
		return new AttackAction(target, direction);
	}
}
