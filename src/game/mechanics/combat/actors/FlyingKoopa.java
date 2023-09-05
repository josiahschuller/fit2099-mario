package game.mechanics.combat.actors;

import game.core.misc.Status;

/**
 * A variation of a Koopa which can fly. It has slightly more hit points
 * and a  JUMP FREELY capability, enabling it to "fly", or traverse
 * high ground with 100% success rate and no fall damage
*/
public class FlyingKoopa extends Koopa {
    
    public FlyingKoopa(){
        super("Flying Koopa", 'F', 150);
        super.addCapability(Status.JUMP_FREELY);
    };
}
