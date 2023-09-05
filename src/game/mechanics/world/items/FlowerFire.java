package game.mechanics.world.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.core.items.Perishable;
import game.mechanics.world.events.BurnEvent;

public class FlowerFire extends Item implements Burning, Perishable  {

    private static int BURN_DAMAGE = 20;
    private static int DURATION = 3;

    private int turnsLeft;

    public FlowerFire() {
        super("Flower fire", 'v', false);
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
        if(currentLocation.containsAnActor()){
            BurnEvent burnEvent = new BurnEvent(this);
            burnEvent.execute(currentLocation.getActor());
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
