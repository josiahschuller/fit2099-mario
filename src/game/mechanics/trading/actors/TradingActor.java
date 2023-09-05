package game.mechanics.trading.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.core.misc.Status;
import game.mechanics.magical.actors.DrinkingActor;

/*
* An abstract class which enables trading capabilities. Essentially gives those that inherit it a "wallet",
* which can be deposited into, withdrawn from, and the balance of which can be checked
*/
public abstract class TradingActor extends DrinkingActor {

    private int balance;

    /*
    * CAN_TRADE capability is used in when checking if actor should be offered a trade action by vendors
    */
    public TradingActor(String name, char displayChar, int hitPoints, IntrinsicWeapon intrinsicWeapon) {
        super(name, displayChar, hitPoints, intrinsicWeapon);
        this.addCapability(Status.CAN_TRADE);
    }

    /*
    * Returns the actors balance
    */
    public int getBalance() {
        return balance;
    }

    /*
    * Attempts to withdraw specified amount. If withdraw successful (i.e. sufficient funds), return true
    * Otherwise, return false
    */
    public boolean withdraw(int amount){
        boolean success = amount <= balance;
        if(success){
            balance -= amount;
        }
        return success;
    }

    /*
    * Deposit specified amount into the wallet
    */
    public void deposit(int amount){
        balance += amount;
    }
    
}
