package game.mechanics.trading.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;
import game.mechanics.trading.actors.TradingActor;
import game.mechanics.trading.items.Coin;

/* A pickup action for coins, which deposits the coin into the players "wallet".
* Note: Coins aren't actually stored in the players inventory. They are destroyed
* upon pickup and their value is simply added to the Players balance.
*/
public class PickUpCoinAction extends PickUpItemAction {

    private final Coin coin;

    public PickUpCoinAction(Coin coin) {
        super(coin);
        this.coin = coin;
    }

    @Override
	public String execute(Actor actor, GameMap map) {
        // If the actor "can trade" (i.e. has a balance)
        if(actor.hasCapability(Status.CAN_TRADE)){
            // Rmove coin from map and from reset manager
            map.locationOf(actor).removeItem(coin);
            this.coin.removeInstance(); 
            // Perform deposit
            if(actor instanceof TradingActor){
                TradingActor tradingActor = (TradingActor) actor;
                tradingActor.deposit(this.coin.getValue());
            }
        }
        return "Player picks up a coin worth " + String.valueOf(this.coin.getValue()) + ". Your balance is now " + ((TradingActor) actor).getBalance() + "$";
	}

    @Override
    public String menuDescription(Actor actor) {
        return super.menuDescription(actor);
    }
    
}
