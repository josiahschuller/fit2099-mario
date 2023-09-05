package game.mechanics.combat.items;


import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.mechanics.trading.items.Tradable;

// DONE
public class Wrench extends WeaponItem implements Tradable {

     /*
    * value of the wrench in dollars
    */
    private final int VALUE = 200;

    /*
    * Constructor
    */
    public Wrench() {
        super("Wrench", 'w', 50, "hits", 80);
    }

    /*
    * Returns the wrenches value
    */
    @Override
    public int getValue() {
        return VALUE;
    }
    
}
