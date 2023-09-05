package game.mechanics.world.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.mechanics.trading.items.Coin;
import game.mechanics.world.positions.Dirt;

public class StompAction extends Action {

    private Location moveToLocation;

    private String direction;

    public StompAction(Location moveToLocation, String direction) {
        this.moveToLocation = moveToLocation;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        moveToLocation.setGround(new Dirt()); // Destroy ground to Dirt
        moveToLocation.addItem(new Coin(5)); // Drop a $5 coin
        map.moveActor(actor, moveToLocation); // Move actor to location

        return actor + " stomps " + this.direction;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves " + this.direction;
    }
}
