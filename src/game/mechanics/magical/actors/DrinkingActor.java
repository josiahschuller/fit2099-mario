package game.mechanics.magical.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

abstract public class DrinkingActor extends Actor {

    /**
     * Intrinsic (base) weapon
     */
    private IntrinsicWeapon intrinsicWeapon;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public DrinkingActor(String name, char displayChar, int hitPoints, IntrinsicWeapon intrinsicWeapon) {
        super(name, displayChar, hitPoints);
        this.intrinsicWeapon = intrinsicWeapon;
    }

    /**
     * Increases the damage of the intrinsic weapon
     * @param damageIncrease amount to increase damage by
     */
    public void increaseBaseDamage(int damageIncrease) {
        this.intrinsicWeapon = new IntrinsicWeapon(this.intrinsicWeapon.damage() + damageIncrease, this.intrinsicWeapon.verb());
    }

    /**
     * Creates and returns an intrinsic weapon.
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Actor with more interesting descriptions and/or different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return this.intrinsicWeapon;
    }

}
