package game.mechanics.world.events;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import game.core.events.Event;
import game.core.misc.Status;
import game.mechanics.world.items.Burning;

public class BurnEvent extends Event {

    private Item item;
    private Ground ground;
    private int burnDamage;

    public BurnEvent(Item item){
        super(item);
        this.item = item;
        this.ground = null;
        this.burnDamage = ((Burning) item).getBurnDamage();
    }

    public BurnEvent(Ground ground){
        super(ground);
        this.ground = ground;
        this.item = null;
        this.burnDamage = ((Burning) ground).getBurnDamage();
    }

    @Override
    public String execute(Actor actor) {
        String result;
        if(actor.hasCapability(Status.IMMUNITY)){
            result = actor + "is immune to fire.";
        }
        else{
            actor.hurt(burnDamage);
            result = this.menuDescription(actor);
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been burned by " 
        + ((ground == null) ? item.toString() : ground.toString()) 
        + " for " + String.valueOf(burnDamage) + " damage";
    }
    
}
