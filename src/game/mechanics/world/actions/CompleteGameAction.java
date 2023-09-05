package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class CompleteGameAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "You have completed the game!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Complete the game";
    }
    
}
