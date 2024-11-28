package MainGame;

import GUI.ControlPanel;
import Levels.GameLevel;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import PlayerInteract.Controller;
import PlayerInteract.PlayChar;
import city.cs.engine.SoundClip;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;

import javax.swing.JFrame;

//The main class of the game.
public class Game {
    private GameLevel world;
    private int level;
    //the ViewElement is how the user will see the game as well as how information such as collectible count and
    //hitpoints will be displayed
    private ViewElement view;
    private Controller controller;
    private SoundClip gameMusic;
    //when initialised the game will default load the first level
    public Game(){
        level = 1;
        world = new Level1();
        world.populate(this);
//        world.getLevelMusic().loop();
        //creates the window
        view = new ViewElement(world,world.getPlayer(),500,500);
        JFrame frame = new JFrame("Video Game: multi level");
        //implements the GUI buttons
        ControlPanel gui = new ControlPanel(this);
        frame.add(gui.getMenu(), BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        //view.setGridResolution(1);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.requestFocus();
        //allows the user to control the player, by attaching keylistener inputs to the player object.
        controller = new Controller(world.getPlayer(),world, this);
        //world.addStepListener(new Tracker(view,world.getPlayer()));
        frame.addKeyListener(controller);
        //implements parallax scrolling using a tracker on the player
        world.addStepListener(new Tracker(view,world.getPlayer()));
        world.start();

    }
    //returns the current worlds player
    public PlayChar getPlayer(){return world.getPlayer();}
    //returns if the user can progress to the next level
    public boolean isCurrentLevelCompleted(){return world.isCompleted();}
    //when called, replaces the current world with the next level.
    public int getLevel(){return level;}
    public void goNextLevel(){
        world.stop();
        //world.stopLevelMusic();
        if(level==3){
            System.exit(0);
        }else if(level==0) {
            level++;
            //The carry variable here is to allow the player's collectible count to progress to the next level.
            //it is not done for the hitpoints aswell to make the game seem fair.
            int CARRY = this.getPlayer().getCollcount();
            world = new Level1();
            world.populate(this);
            controller.setBody(world.getPlayer());
            world.getPlayer().setcollcount(CARRY);
            view.setWorld(world);
            world.addStepListener(new Tracker(view,world.getPlayer()));
            view.setPlayer(this.getPlayer());
            controller.setWorld(world);
            world.start();
        } else if(level==1) {
            level++;
            int CARRY = this.getPlayer().getCollcount();
            world = new Level2();
            world.populate(this);
            controller.setBody(world.getPlayer());
            world.getPlayer().setcollcount(CARRY);
            view.setWorld(world);
            world.addStepListener(new Tracker(view,world.getPlayer()));
            view.setPlayer(this.getPlayer());
            controller.setWorld(world);
            world.start();
        }else if(level==2) {
            level++;
            int CARRY = this.getPlayer().getCollcount();
            world = new Level3();
            world.populate(this);
            controller.setBody(world.getPlayer());
            world.getPlayer().setcollcount(CARRY);
            view.setWorld(world);
            world.addStepListener(new Tracker(view,world.getPlayer()));
            view.setPlayer(this.getPlayer());
            controller.setWorld(world);
            world.start();
        }
        //world.getLevelMusic().loop();
    }
    //these functions are called when the player uses the GUI
    public void Pause(){ world.stop();}
    public void Resume() {world.start(); }
    public void Quit(){
        System.exit(0);
    }
    public void advanceLevel(){
        goNextLevel();
    }
    //by creating a new levelstate of 0, it can allow the user to move straight to the first level again.
    public void Restart(){
        level=0;
        //world.stopLevelMusic();
        goNextLevel();
        this.getPlayer().resetcollcount();
    }
    public void goToLevel(GameLevel lev){
        world.stop();
        world.stopLevelMusic();
        level = lev.getLevelNumber();
        world = lev;
        controller.setBody(world.getPlayer());
        controller.setWorld(world);
        view.setWorld(world);
        world.addStepListener(new Tracker(view,world.getPlayer()));
        view.setPlayer(this.getPlayer());
        world.start();
    }
    public GameLevel getWorld(){return world;}

    public static void main(String[] args) {
        new Game();

    }
}


