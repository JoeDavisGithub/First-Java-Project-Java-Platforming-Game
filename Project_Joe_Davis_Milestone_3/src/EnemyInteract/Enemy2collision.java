package EnemyInteract;
import city.cs.engine.*;
import PlayerInteract.Controller;
import PlayerInteract.PlayChar;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//the collision for the flyer type enemy
public class Enemy2collision implements CollisionListener{
    private PlayChar knight;
    private SoundClip enemy2attack;
    private Enemy2 enemy;
    //the current player and enemy object are needed to make the collisions specific.
    public Enemy2collision(PlayChar knight, Enemy2 enemy){
        this.knight = knight;
        this.enemy = enemy;
    }
    @Override
    public void collide(CollisionEvent e) {
        //if the collision is a knight, it will take 2 hitpoints off of the player
        if (e.getOtherBody() == knight) {
            knight.AttackPlayer(2);
            Controller.attacked();
            //if the player is now dead, it will exit the game
            if (knight.getHitpoints() <= 0) {
                e.getOtherBody().destroy();
                System.exit(0);
            }
            try {
                enemy2attack = new SoundClip("Enemy2Attack.wav");   // Open an audio input stream
                enemy2attack.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ea) {
                System.out.println(ea);
            }
            //it will also change direction after attacking the player both vertically and horizontally
            enemy.stopWalking();
            enemy.removeAllImages();
            enemy.addImage(new BodyImage(enemy.changeimage(), 2f));
            enemy.setflightspeed();
            System.out.println(enemy.getflightspeed());
            enemy.startWalking(enemy.getflightspeed());
            enemy.setLinearVelocity(new Vec2(0, enemy.getupdown()));
        } else if(e.getOtherBody()!= knight){
            //if the enemy collides with a wall, it will only change horizontal direction if it hits a wall
            //to the left or right of it, by detecting when the horizontal velocity becomes 0
            if(enemy.getLinearVelocity().x ==0) {
                enemy.stopWalking();
                enemy.removeAllImages();
                enemy.addImage(new BodyImage(enemy.changeimage(), 2f));
                enemy.setflightspeed();
                System.out.println(enemy.getflightspeed());
                enemy.startWalking(enemy.getflightspeed());
            }
            //and therefore changes vertical direction by detecting when it hits a ceiling by detecting if the
            //vertical velocity is 0
            if(enemy.getLinearVelocity().y ==0) {
                enemy.setupdown(-enemy.getupdown());
                enemy.setLinearVelocity(new Vec2(0, enemy.getupdown()));
            }
        }
    }

}
