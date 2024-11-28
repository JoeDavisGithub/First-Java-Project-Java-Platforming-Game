package Levels;

import CollectibleInteract.Collectible;
import EnemyInteract.Enemy1;
import EnemyInteract.Enemy2;
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

public class Level2 extends GameLevel {
    private Image background;
    private static final int Num_collec = 11;
    private static final int LevelCheck = 16;
    private SoundClip BGmusic;
    private Enemy1 enemy;
    private Enemy2 enemy2;
    @Override
    public void populate(Game game){
        super.populate(game);
        this.background = new ImageIcon("Level2Bg.jpg").getImage();
        Platform ground = new Platform(this, 11f,0.5f);
        ground.setPosition(new Vec2(0, -11.5f));
        Platform leftwall = new Platform(this,0.5f,30f);
        leftwall.setPosition(new Vec2(-11.5f, 0f));
        Platform rightwall = new Platform(this,0.5f,30f);
        rightwall.setPosition(new Vec2(11.5f,0f));

        Platform plat1 = new Platform(this,7f,0.5f);
        plat1.setPosition(new Vec2(-4.5f, 0.5f));

        Platform plat2 = new Platform(this,4f,0.5f);
        plat2.setPosition(new Vec2(5, -3.5f));

        Platform plat3 = new Platform(this,7f,0.5f);
        plat3.setPosition(new Vec2(-4.5f,-8f));

        Platform plat3left = new Platform(this,0.3f,0.3f);
        plat3left.setPosition(new Vec2(-11.2f,-7.7f));
        Platform plat3right = new Platform(this,0.3f,0.3f);
        plat3right.setPosition(new Vec2(2.2f,-7.7f));
        //level 2 implements the first enemy, which is created, moved to a specific location and given a collision listener.
        enemy = new Enemy1(this);
        enemy.setPosition(new Vec2(-6f, -1f));
        enemy.addCollisionListener(new Enemycollision(this.getPlayer(),enemy));
       /* enemy2 = new Enemy2(this,-10f);
        enemy2.setPosition(new Vec2(-4,enemy2.getalt()));
        enemy2.addCollisionListener(new Enemy2collision(this.getPlayer(),enemy2));*/
       for (int i = 0; i < Num_collec; i++) {
            Body collectible = new Collectible(this);
            collectible.setPosition(new Vec2(i * 2 - 10, 10));
            collectible.addCollisionListener(new Pickup(getPlayer()));
        }
    }
    @Override
    public Vec2 startPosition() {
        return new Vec2(8, -10);
    }
    @Override
    public Vec2 doorPosition() {
        return new Vec2(-10.4f, -9.6f);
    }
    @Override
    public boolean isCompleted() {
        return getPlayer().getCollcount() == LevelCheck;
    }
    @Override
    public void setBackground(){
        this.background = new ImageIcon("Level2Bg.jpg").getImage();
    }
    @Override
    public Image getBackgroundImage(){return this.background;}
    @Override
    public int getLevelNumber(){return 2;}
    @Override
    public SoundClip getLevelMusic(){try {
        BGmusic = new SoundClip("BGmusic2.wav");   // Open an audio input stream
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ea) {
        System.out.println(ea);
    } return BGmusic;}
    @Override
    public void stopLevelMusic(){BGmusic.stop();}
}
