package game.mechanics.magical.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.behaviours.Behaviour;
import game.mechanics.magical.actions.DrinkAction;
import game.mechanics.world.positions.Fountain;

public class DrinkBehaviour implements Behaviour {

    /**
     * Returns a DrinkAction to drink water from fountain, if possible.
     * If not possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no DrinkAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (map.locationOf(actor).getGround() instanceof Fountain) {
            Fountain fountain = (Fountain) map.locationOf(actor).getGround();
            if (fountain.isActive()) {
                return new DrinkAction(fountain.takeWater());
            }
        }
        return null;
    }
}
