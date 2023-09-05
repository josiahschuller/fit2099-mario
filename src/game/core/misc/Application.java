package game.core.misc;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.core.actors.Player;
import game.mechanics.combat.actors.Bowser;
import game.mechanics.magical.items.PowerStar;
import game.mechanics.magical.items.SuperMushroom;
import game.mechanics.trading.actors.Toad;
import game.mechanics.world.actors.PrincessPeach;
import game.mechanics.world.misc.TeleportManager;
import game.mechanics.world.positions.*;
import game.mechanics.world.positions.trees.Tree;

/**
 * The main class for the Mario World game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), Tree.createTree(), new WarpPipe());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                ".................................................##.............................",
                "................................................##..............................",
                ".........+..............................+###....##.................+............",
                ".........................................#_#....#++.............................",
                ".......................................+.###....##..............................",
                "........................................+._____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");

        FancyGroundFactory groundFactoryLava = new FancyGroundFactory(new Dirt(), new Lava(), new WarpPipe());

        List<String> mapLava = Arrays.asList(
                "...L.........L..",
                ".........L......",
                "..L..L..........",
                ".........L......",
                ".....L........L.",
                "..L.............",
                "......L.....L...",
                "......L....L....");

        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap gameMapLava = new GameMap(groundFactoryLava, mapLava);
        world.addGameMap(gameMap);
        world.addGameMap(gameMapLava);

        int MARIO_SPAWN_X = 38;
        int MARIO_SPAWN_Y = 10;

        int TOAD_SPAWN_X = 42;
        int TOAD_SPAWN_Y = 10;

        Actor mario = new Player("Player", 'm', 100);
        //mario.addItemToInventory(new Bottle()); // Give Mario a bottle
        world.addPlayer(mario, gameMap.at(MARIO_SPAWN_X, MARIO_SPAWN_Y));

        // Place Super Mushroom and Power Star
        Item superMushroom = new SuperMushroom();
        Item powerStar = new PowerStar();
        gameMap.at(MARIO_SPAWN_X, MARIO_SPAWN_Y).addItem(superMushroom);
        gameMap.at(MARIO_SPAWN_X, MARIO_SPAWN_Y).addItem(powerStar);

        // Place fountains
        gameMap.at(37,8).setGround(new HealthFountain());
        gameMap.at(39,8).setGround(new PowerFountain());

        // Spawn Toad in the wall square
        gameMap.at(TOAD_SPAWN_X, TOAD_SPAWN_Y).addActor(new Toad());

        // Add Warp Pipes
        int warpPipeLavaX = 0;
        int warpPipeLavay = 0;
        gameMapLava.at(warpPipeLavaX, warpPipeLavay).setGround(new WarpPipe()); // Warp pipe in lava map
        gameMap.at(35, 8).setGround(new WarpPipe());
        gameMap.at(35, 12).setGround(new WarpPipe());

        // Setup Teleport Manager and set target to WarpPipe in lava map
        TeleportManager teleportManager = TeleportManager.getInstance();
        teleportManager.setTargetLocation(gameMapLava.at(warpPipeLavaX, warpPipeLavay));

        // Add bowser and peach
        gameMapLava.addActor(new Bowser(), gameMapLava.at(2, 2));
        gameMapLava.addActor(new PrincessPeach(), gameMapLava.at(2, 3));

        world.run();

    }
}
