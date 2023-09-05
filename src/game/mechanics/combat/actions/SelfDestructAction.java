package game.mechanics.combat.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/* Removes actor from map */
public class SelfDestructAction extends Action {


    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor.toString() + " died";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Self-destruct";
    }
    
}
