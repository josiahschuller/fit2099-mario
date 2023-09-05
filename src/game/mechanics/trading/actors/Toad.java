package game.mechanics.trading.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.core.misc.Status;
import game.mechanics.combat.items.Wrench;
import game.mechanics.magical.actions.CollectBottleAction;
import game.mechanics.magical.items.PowerStar;
import game.mechanics.magical.items.SuperMushroom;
import game.mechanics.trading.actions.TradeAction;

/*The games vendor, which offers various items for sale*/
public class Toad extends Actor {

    public Toad(){
        super("Toad", 'O', Integer.MAX_VALUE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /*  If the actor near toad is a trading actor, offer the three items for sale
    * in the form of trading actions.
    */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = new ActionList();
        
        if(otherActor.hasCapability(Status.CAN_TRADE)){

            actionList.add(new TradeAction(new SuperMushroom()));
            actionList.add(new TradeAction(new PowerStar()));
            actionList.add(new TradeAction(new Wrench())); 

            if(!otherActor.hasCapability(Status.HAS_BOTTLE)) {
                actionList.add(new CollectBottleAction());
            }
        }
        return actionList;
        
    }
    
}
