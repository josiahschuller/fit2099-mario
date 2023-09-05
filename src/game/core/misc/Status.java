package game.core.misc;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    IS_PLAYER, // use this status to mark the player
    HOSTILE_TO_ENEMY, // use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
    TALL, // use this status to tell that current instance has "grown".
    CAN_JUMP, // use this status to be able to jump
    JUMP_FREELY, // use this status to jump freely with 100% success rate and no fall damage.
    SUPER_WALK, // use this status to walk to high grounds and destroy them, generating a coin.
    STOMPABLE, // use this status for grounds that can be stomped
    IMMUNITY, // use this status to be immune to enemy attacks.
    INSTA_KILL, // use this status to kill enemies instantly with a successful attack.
    CAN_TRADE, // use this status to be able to pick up coins and trade with Toad
    HAS_WRENCH, // use this status to enable player to destroy the koopa shell if they have a wrench
    DORMANT, // use this status to represent Koopa being defeated
    HAS_BOTTLE, // use this status to represent an actor that has a bottle
    FIRE_ATTACK, // use this status to give an actor the ability to use a fire attack
    CAN_END_GAME, // use this status to give an actor the capability to complete the game
    RESET // use this status to mark an object to be reset
}
