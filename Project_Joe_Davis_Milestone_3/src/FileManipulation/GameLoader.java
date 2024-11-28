package FileManipulation;

import CollectibleInteract.Collectible;
import CollectibleInteract.Pickup;
import DoorInteract.Door;
import DoorInteract.DoorListener;
import EnemyInteract.Enemy1;
import EnemyInteract.Enemy2;
import EnemyInteract.Enemy2collision;
import EnemyInteract.Enemycollision;
import Levels.*;
import MainGame.Game;
import PlayerInteract.PlayChar;
import city.cs.engine.Body;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * GameLoader loads saved level state from a file in the project's directory
 */
public class GameLoader {
    /**
     * player references the body that will be used as the player controlled character
     */
    private PlayChar player;
    /**
     * filename is the string used to reference which saved state will be loaded, naturally there is only one possible file
     */
    private String fileName;
    /**
     * game is the window that the world will be created within
     */
    private Game game;

    /**
     * Gameloader's constructor is used to set the Game and filename that will be used in a later method
     * @param fileName a String variable used to find a file
     * @param g a Game object which the game runs through.
     */
    public GameLoader(String fileName, Game g) {
        this.fileName = fileName;
        game = g;
    }

    /**
     * <p>LoadGame() method imitates a level's populate method, but only creates the bodies saved in the save file,
     * including staticbodies such as platforms. it uses a series of if statements to create different types of
     * bodies dependent on classes and location using Vec2 values</p>
     * @return it returns an instance of a Gamelevel object, this is because the save can be level 1, 2 or 3.
     * @throws IOException
     */
    public GameLevel LoadGame() throws IOException {
        /**
         * file reader is used to access the savefile
         */
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);

            String line = reader.readLine();
            int levelNumber = Integer.parseInt(line);
            /**
             * level is set to null originally but then chosen later after the file is read it is set to the corresponding level class
             */
            GameLevel level = null;

            if (levelNumber == 1) {
                level = new Level1();
                level.setBackground();
            } else if (levelNumber == 2) {
                level = new Level2();
                level.setBackground();
            } else if (levelNumber == 3) {
                level = new Level3();
                level.setBackground();
            }
            level.getLevelMusic().loop();
            while((line = reader.readLine())!= null){
                String[] tokens = line.split(",");
                String className = tokens[0];
                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);

                Vec2 pos = new Vec2(x,y);
                if(className.equals("PlayChar")){
                    int coll = Integer.parseInt(tokens[3]);
                    int hit = Integer.parseInt(tokens[4]);
                    player = new PlayChar(level);
                    player.setPosition(new Vec2(pos));
                    player.setcollcount(coll);
                    player.setHitpoints(hit);
                    level.setPlayer(player);
                }
                if(className.equals("Enemy1")){
                    Enemy1 b = new Enemy1(level);
                    b.setPosition(new Vec2(pos));
                    b.addCollisionListener(new Enemycollision(player,b));
                }
                if(className.equals("Enemy2")){
                    Enemy2 b = new Enemy2(level);
                    b.setPosition(new Vec2(pos));
                    b.addCollisionListener(new Enemy2collision(player,b));
                }
                if(className.equals("Collectible")){
                    Collectible b = new Collectible(level);
                    b.addCollisionListener(new Pickup(level.getPlayer()));
                    b.setPosition(new Vec2(pos));
                }
                if(className.equals("Platform")){
                    float Width = Float.parseFloat(tokens[3]);
                    float Height = Float.parseFloat(tokens[4]);
                    Body b = new Platform(level,Width,Height);
                    b.setPosition(new Vec2(pos));
                }
                if(className.equals("Door")){
                    Door b = new Door(level);
                    b.setPosition(new Vec2(pos));
                    b.addCollisionListener(new DoorListener(game));
                }

            }


            return level;


        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }

    }
}