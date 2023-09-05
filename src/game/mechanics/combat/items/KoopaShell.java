package game.mechanics.combat.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.core.misc.Status;
import game.mechanics.combat.actions.DestroyShellAction;

/**
 * Koopa's shell, which is dropped when it's defeated. It can be destroyed if Mario
 * has a wrench, and it will drop a super mushroom*/
public class KoopaShell extends Item{

    /*
    * Constructor
    */
    public KoopaShell() {
        super("Koopa Shell", 'D', true);
    }

    /**
     * The shell's pickup action is actually a destroy shell action. The shell will
     * check if Mario has a wrench before offering this action.
    */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        if(actor.hasCapability(Status.HAS_WRENCH)){
            return new DestroyShellAction(this);
        }
        else{
            return null;
        }
    }
    
}
