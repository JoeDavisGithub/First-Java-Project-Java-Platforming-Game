package EnemyInteract;

import city.cs.engine.*;
import PlayerInteract.Controller;
import PlayerInteract.PlayChar;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//collision for the walker type enemy
public class Enemycollision implements CollisionListener {
    private PlayChar knight;
    private Enemy1 enemy;
    private SoundClip enemyattack;
    public Enemycollision(PlayChar knight, Enemy1 enemy) {
        this.knight = knight;
        this.enemy = enemy;
    }

    @Override
    public void collide(CollisionEvent e) {
        //if the player collides with the enemy it will remove 1 hitpoint from the player and exit the game if the
        //players health falls to 0
        if (e.getOtherBody() == knight) {
            knight.AttackPlayer(1);
            Controller.attacked();
            if (knight.getHitpoints() <= 0) {
                e.getOtherBody().destroy();
                System.exit(0);
            }
            try {
                enemyattack = new SoundClip("Enemy1Attack.wav");   // Open an audio input stream
                enemyattack.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ea) {
                System.out.println(ea);
            }
            // if the enemy collides with a wall it will simply change direction.
        } else if(e.getOtherBody()!= knight){
            enemy.stopWalking();
            enemy.removeAllImages();
            enemy.addImage(new BodyImage(enemy.changeimage(), 4f));
            enemy.setSpeed(enemy.getDirection());
            enemy.startWalking(enemy.getWalking());
            Vec2 v = enemy.getLinearVelocity();
            if (v.x==0){
                enemy.startWalking(enemy.getWalking());
            }
        }
        }
    }



