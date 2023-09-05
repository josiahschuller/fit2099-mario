package game.mechanics.trading.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.mechanics.trading.actors.TradingActor;
import game.mechanics.trading.items.Tradable;

/* An action provided to the player by a vendor, allowing them to attempt to by an item */
public class TradeAction extends Action {

    /*The item for sale and its value */
    private Item item;
    private int value;

    public TradeAction(Item item){
        this.item = item;
        this.value = ((Tradable) item).getValue();
    }

    @Override
    public String execute(Actor buyer, GameMap map) {
        String message;
        // Attempt to withdraw. If unsuccessful, print failure message
        boolean withdrawSuccess = ((TradingActor) buyer).withdraw(value);
        if(!withdrawSuccess){
            message = "You don't have enough coins!";
        }
        // If uccesful, add the item to the players inventory (via pickup action) and display relevant message
        else{
            item.getPickUpAction(buyer).execute(buyer, map);
            message = "You bought " + item.toString() + " for " + String.valueOf(value);
        }

        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + item.toString() + " for " + String.valueOf(value);
    }
    
}
