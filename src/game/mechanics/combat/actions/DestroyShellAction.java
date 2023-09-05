package game.mechanics.combat.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.magical.items.SuperMushroom;

/* Available if Koopa is dormant and player has a wrench. Drops a super mushroom in Koopa's place */
public class DestroyShellAction extends PickUpItemAction {

    Item item;

    public DestroyShellAction(Item item) {
        super(item);
        this.item = item;
    }
    
    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem(item);
        map.locationOf(actor).addItem(new SuperMushroom());
        return item + " is destroyed and drops a Super Mushroom!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Destroy shell";
    }
}
