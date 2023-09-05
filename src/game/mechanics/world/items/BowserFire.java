package game.mechanics.world.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.core.items.Perishable;
import game.mechanics.combat.actors.Bowser;
import game.mechanics.world.events.BurnEvent;

public class BowserFire extends Item implements Burning, Perishable {
    
    private static int BURN_DAMAGE = 20;
    private static int DURATION = 3;

    private int turnsLeft;

    public BowserFire() {
        super("Bowser fire", 'v', false);
        this.setTurnsLeft(DURATION);
    }

    @Override
    public void decreaseTurnsLeft() {
        turnsLeft --;
    }

    @Override
    public int getTurnsLeft() {
        return turnsLeft;
    }

    @Override
    public void setTurnsLeft(int turnsLeft) {
        this.turnsLeft = turnsLeft;
        
    }

    @Override
    public void tick(Location currentLocation) {
        Actor actor = currentLocation.getActor();
        if(actor != null && !(actor instanceof Bowser)){
            BurnEvent burnEvent = new BurnEvent(this);
            burnEvent.execute(actor);
        }
        this.decreaseTurnsLeft();
        if(this.getTurnsLeft() == 0){
            currentLocation.removeItem(this);
        }
    }

    @Override
    public int getBurnDamage() {
        return BURN_DAMAGE;
    }
}
