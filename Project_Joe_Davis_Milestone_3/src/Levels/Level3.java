package Levels;
import CollectibleInteract.Collectible;
import EnemyInteract.Enemy1;
import EnemyInteract.Enemy2;
import EnemyInteract.Enemy2collision;
import EnemyInteract.Enemycollision;
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

//this level is where the game implements the second enemy type as well as a less "tutorial" type of convention
public class Level3 extends GameLevel {
    private static final int Num_collec = 6;
    private static final int LevelCheck = 22;
    private SoundClip BGmusic;
    private Image background;
    private Enemy1 enemy1;
    //each enemy type has a list of locations that they will originate from
    private Vec2[] spawnlist1 = {new Vec2(-7.5f,-7f),new Vec2(7.5f,-7f)};
    private Enemy2 enemy2;
    private Vec2[] spawnlist2 = {new Vec2(-7.5f,-10f),new Vec2(4f,0),new Vec2(-4,0)};
    //the platforms also have this, for the sake of convenient level implementation to reduce code duplication.
    private Vec2[] platformpositions={new Vec2(-7.5f,-8.5f),new Vec2(-7.5f,-2),new Vec2(7.5f,-8.5f),new Vec2(7.5f,-2f)};
    @Override
    public void populate(Game game){
        super.populate(game);
        this.background = new ImageIcon("Level3Bg.jpg").getImage();
        //these are the level boundaries
        Platform ground = new Platform(this,15f,0.5f);
        ground.setPosition(new Vec2(0f,-12.5f));

        Platform ceiling = new Platform(this,15f,0.5f);
        ceiling.setPosition(new Vec2(0f,2f));

        Platform leftwall = new Platform(this,0.5f,7.5f);
        leftwall.setPosition(new Vec2(-14.5f,-5.5f));

        Platform rightwall = new Platform(this,0.5f,7.5f);
        rightwall.setPosition(new Vec2(14.5f,-5.5f));

        //this loop creates identical platforms with their ledges in the desired locations.
        for(int i=0;i<4;i++){
            Platform platform = new Platform(this, 3.5f,0.5f);
            platform.setPosition(platformpositions[i]);
            Platform ledgeL = new Platform(this,0.3f,0.3f);
            ledgeL.setPosition(new Vec2(platformpositions[i].x-3.2f,platformpositions[i].y+0.3f));
            Platform ledgeR = new Platform(this,0.3f,0.3f);
            ledgeR.setPosition(new Vec2(platformpositions[i].x+3.2f,platformpositions[i].y+0.3f));
        }
        Platform midplat = new Platform(this,2f,0.5f);
        midplat.setPosition(new Vec2(0f,-5.5f));
        //this loop creates all Enemy1 bodies in the correct locations
        for(int i=0;i<2;i++){
            enemy1 = new Enemy1(this);
            enemy1.setPosition(spawnlist1[i]);
            enemy1.addCollisionListener(new Enemycollision(this.getPlayer(),enemy1));
        }
        //this loop creates all Enemy2 bodies in the correct locations.
        for(int i=0;i<3;i++){
            enemy2 = new Enemy2(this);
            enemy2.setPosition(spawnlist2[i]);
            enemy2.addCollisionListener(new Enemy2collision(this.getPlayer(),enemy2));
        }
        //this loop creates the collectible bodies but separates their distribution so they appear on the top two platforms
        for(int i = 0; i<Num_collec;i++){
            Body collectible = new Collectible(this);
            collectible.addCollisionListener(new Pickup(getPlayer()));
            if(i<3){
                collectible.setPosition(new Vec2(-9+(i*0.5f), 1f));
            } else{
                collectible.setPosition(new Vec2(5+(i*0.5f), 1f));
            }
        }
    }
    @Override
    public Vec2 startPosition() { return new Vec2(0,-10);}
    @Override
    public Vec2 doorPosition() {
        return new Vec2(-10.4f, -10.5f);
    }
    @Override
    public boolean isCompleted() {
        return getPlayer().getCollcount() == LevelCheck;
    }
    @Override
    public void setBackground(){
        this.background = new ImageIcon("Level3Bg.jpg").getImage();
    }
    @Override
    public Image getBackgroundImage(){return this.background;}
    @Override
    public int getLevelNumber(){return 3;}
    @Override
    public SoundClip getLevelMusic(){try {
        BGmusic = new SoundClip("BGmusic3.wav");   // Open an audio input stream
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ea) {
        System.out.println(ea);
    } return BGmusic;}
    @Override
    public void stopLevelMusic(){BGmusic.stop();}
}
