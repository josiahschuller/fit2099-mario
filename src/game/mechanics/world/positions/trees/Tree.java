package game.mechanics.world.positions.trees;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.core.misc.Status;
import game.mechanics.reset.Resettable;
import game.mechanics.world.items.FireFlower;
import game.mechanics.world.positions.Dirt;
import game.mechanics.world.positions.HigherGround;

abstract public class Tree extends HigherGround implements Resettable {

    /**
     * The age of the tree
     *
     */
    private int age;

    private static double DROP_FIRE_FLOWER_CHANCE = 0.5;

    /**
     * Random number generator for simulating random events (e.g. spawning, withering, etc.).
     *
     */
    private Random rand = new Random();

    /**
     * Constructor.
     * @param displayChar - the display character of the tree (dependant on its growth stage)
     * @param age - the initial age of the tree (dependant on its growth stage)
     */
    public Tree(char displayChar, int age) {
        super(displayChar);
        this.age = age;
        this.addCapability(Status.STOMPABLE);
    }

    /** 
     * A static factory used to instantiate a tree. Returns the tree subclass that
     * represents the first growth stage. If first growth stage changes to something
     * else (e.g. "Seed"), this will not change the way trees are created, and the
     * otuside code can still create a new tree with Tree.CreateTree() 
    **/
    public static Tree createTree(){
        return new Sprout();
    }

     /** 
     * increments the trees age with every turn
    **/
    @Override
    public void tick(Location location) {
        attemptToDrop(location, new FireFlower(), DROP_FIRE_FLOWER_CHANCE);
        grow();
        super.tick(location);

        if (this.hasCapability(Status.RESET)) {
            // If tree is being reset, convert back to Dirt
            location.setGround(new Dirt());
        }
    }

    /** 
     * increments the trees age
    **/
    public void grow(){
        age ++;
    }

    /** 
     * returns the trees age
    **/
    public int getAge(){
        return age;
    }

     /** 
     * Attempts to spawn an actor with a chance
     * 
     * @param actor - actor to be spawned
     * @param location - trees location (where actor will be spawned)
     * @param chance - chance of spawning the actor
    **/
    public void attemptToSpawn(Location location, Actor actor, double chance){
        if(simulateRandomEvent(chance) && !location.containsAnActor()){
            location.addActor(actor);
        }
    }

    /** 
     * Attempts to drop item with a chance
     * 
     * @param item - item to be dropped
     * @param location - trees location (where item will be dropped)
     * @param chance - chance of dropping the item
    **/
    public void attemptToDrop(Location location, Item item, double chance){
        if(simulateRandomEvent(chance)){
            location.addItem(item);
        }
    }

    /** 
     * Attempts to wither (i.e. self-destruct)
     * 
     * @param location - trees location
     * @param chance - chance of withering
    **/
    public void attemptToWither(Location location, double chance){
        if(simulateRandomEvent(chance)){
            this.removeInstance(); // Remove instance from being resettable
            location.setGround(new Dirt());
        }
    }

    /** 
     * Attempts to spread to a nearby location (if any of them are Dirt)
     * 
     * @param location - trees location
    **/
    public void attemptToSpread(Location location){
        // An empty list of potential locations to spread to
        ArrayList<Location> freeLocations = new ArrayList<Location>();
        // Check each exit. If the exit is dirt, this tree can spread there
        for (Exit exit : location.getExits()) {
			Location destination = exit.getDestination();
            if(destination.getGround() instanceof Dirt){
                freeLocations.add(destination);
            }
		}
        // Randomly spread to one of the nearby dirt locations
        freeLocations.get(rand.nextInt(freeLocations.size())).setGround(Tree.createTree());;
    }

    /** 
     * "Simulates" an event with a given chance
     * 
     * @param chance - probability of event happening
     * @return whether or not the event happened (boolean)
    **/
    protected boolean simulateRandomEvent(double chance){
        double randomProbability = rand.nextDouble();
        return (randomProbability <= chance) ? true : false;
    }

    /**
     * This method is called when the user uses a ResetAction and implements the functionality for being reset.
     */
    public void resetInstance() {
        if (this.simulateRandomEvent(0.5)) {
            this.addCapability(Status.RESET); // Mark tree to be reset
        }
    }
}
