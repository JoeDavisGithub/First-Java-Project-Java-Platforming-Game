package Levels;

import DoorInteract.Door;
import DoorInteract.DoorListener;
import MainGame.Game;
import MainGame.Tracker;
import PlayerInteract.PlayChar;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
//this is the class that all levels will be subclasses of, declaring variables and methods that will be universal
//for all levels of the game.
public abstract class GameLevel extends World {
    private PlayChar knight;
    private static Image background;
    public PlayChar getPlayer(){return knight;}
    public void setPlayer(PlayChar player){knight = player;}
    //method called when a level is built.
    public void populate(Game game){
        //creates a new player in the world.
        knight = new PlayChar(this);
        //moves the player to the level specific location
        knight.setPosition(startPosition());
        //does the same for the level door
        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));

    }
    public abstract void setBackground();
    public abstract Vec2 startPosition();
    public abstract Vec2 doorPosition();
    public abstract boolean isCompleted();
    public abstract Image getBackgroundImage();
    public abstract int getLevelNumber();
    public abstract SoundClip getLevelMusic();
    public abstract void stopLevelMusic();

}
