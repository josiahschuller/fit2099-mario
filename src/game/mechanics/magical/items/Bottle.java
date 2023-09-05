package game.mechanics.magical.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.mechanics.magical.actions.DrinkFromBottleAction;
import game.mechanics.trading.items.Tradable;

import java.util.Stack;

public class Bottle extends Item implements Tradable {

    private Stack<Water> contents;

    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        this.contents = new Stack<Water>();
    }

    /**
     * Returns whether the bottle is empty or not
     * @return boolean of whether the bottle is empty
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /**
     * Fill the bottle contents with more water
     * @param water water to be added
     */
    public void fill(Water water) {
        if (this.getAllowableActions().isEmpty()) {
            this.addAction(new DrinkFromBottleAction());
        }
        contents.push(water);
    }

    /**
     * Actor drinks water from the bottle
     * @return water at top of the bottle
     */
    public Water drink() {
        // Pop water from top of contents
        Water water = this.contents.pop();

        // Remove DrinkFromBottleAction from allowable actions if no water left
        if (this.contents.isEmpty()) {
            for (Action action: this.getAllowableActions()) {
                if (action instanceof DrinkFromBottleAction) {
                    this.removeAction(action);
                    break;
                }
            }
        }
        return water;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
