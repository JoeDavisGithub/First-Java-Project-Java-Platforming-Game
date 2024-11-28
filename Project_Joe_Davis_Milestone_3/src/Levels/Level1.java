package Levels;
import CollectibleInteract.Collectible;
import city.cs.engine.*;
import city.cs.engine.Shape;
import MainGame.Game;
import CollectibleInteract.Pickup;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * class that level 1 worlds are instances of
 */
public class Level1 extends GameLevel {
    /**
     * Num_collec is the number of collectibles to be created in the level
     */
    private static final int Num_collec = 5;
    /**
     * LevelCheck is the number of collectibles the player currently needs to progress;
     */
    private static final int LevelCheck = 5;
    /**
     * background is the image used as the background of the level to prevent the screen from appearing empty
     */
    private Image background;
    /**
     * BGmusic is the music used in the level, it just makes the game more fun to play with a soundtrack
     */
    private SoundClip BGmusic;

    /**
     *<p> this method is used to turn a level into a specific level, meaning platform, enemy, collectible and player placement
     *are all selected within the populate method. it also changes the background</p>
     * @param game the game being the main window that he world is to be created within.
     */
    @Override
    public void populate(Game game){
        super.populate(game);
        this.background = new ImageIcon("Level1Bg.png").getImage();
        /**
         * Platform is a class used to create static bodies to be used as the level itself, for saving and loading purposes
         */
        Platform ground = new Platform(this,11f,0.5f);
        ground.setPosition(new Vec2(0, -11.5f));

        Platform leftWall = new Platform(this,0.5f,6f);
        leftWall.setPosition(new Vec2(-11.5f, -5.5f));

        Platform rightWall = new Platform(this,0.5f,6f);
        rightWall.setPosition(new Vec2(11.5f, -5.5f));
        //produces the level's number of collectibles in a uniform spread across the stage
        /**
         * for loop automatically generates the level specific number of collectibles
         */
        for(int i = 0; i<Num_collec;i++){
            Body collectible = new Collectible(this);
            collectible.setPosition(new Vec2(i*4-8, 10));
            collectible.addCollisionListener(new Pickup(getPlayer()));
        }
    }
    //these override the values of the abstract variables from GameLevel. making them level specific

    /**
     *startPosition() returns the location that the player starts in the level using a Vec2 variable
     * @return it returns a Vec2 value
     */
    @Override
    public Vec2 startPosition() { return new Vec2(2,-10);}

    /**
     * doorPosition() is used to get the location that the door will be in the level
     * @return returns a Vec2 value
     */
    @Override
    public Vec2 doorPosition() {
        return new Vec2(-10.4f, -9.6f);
    }

    /**
     * isCompleted() is used to verify if a level has enough collectibles to use the door and progress to the next level
     * @return it returns a boolean value of true or false depending on the levelCheck and player collectible count.
     */
    @Override
    public boolean isCompleted() {
        return getPlayer().getCollcount() == LevelCheck;
    }

    /**
     * setBackground() is used to change the background of the stage in the User's view
     */
    @Override
    public void setBackground(){
        this.background = new ImageIcon("Level1Bg.png").getImage();
    }

    /**
     * getBackgroundImage() is used to get the image from the level to be used in setBackground()
     * @return returns an Image variable
     */
    @Override
    public Image getBackgroundImage(){return this.background;}

    /**
     * getLevelNumber() is a verification of which level the world is on
     * @return returns an integer value
     */
    @Override
    public int getLevelNumber(){return 1;}

    /**
     * getLevelMusic() is used to create a SoundClip that is specific to the level, when called it will be looped as the background music
     * @return returns a SoundClip variable
     */
    @Override
    public SoundClip getLevelMusic(){try {
        BGmusic = new SoundClip("BGmusic.wav");   // Open an audio input stream
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ea) {
        System.out.println(ea);
    } return BGmusic;}

    /**
     * stopLevelMusic() stops the SoundClip file from playing, this is used to prevent it from clashing with the next worlds music.
     */
    @Override
    public void stopLevelMusic(){BGmusic.stop();}
}
