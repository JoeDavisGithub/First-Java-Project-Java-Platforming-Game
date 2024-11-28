package CollectibleInteract;

import city.cs.engine.*;
import PlayerInteract.PlayChar;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

//the collision detection for the collectibles, it requires the player body to be identified, done in the constructor.
public class Pickup implements CollisionListener{
    private PlayChar knight;
    private SoundClip Collectiblesound;
    //gets the current world's player
    public Pickup(PlayChar knight){this.knight = knight;}
    //upon a collision, checks that the other body is the knight and calls the correct function, as well as destroys
    //the collectible.
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == knight) {
            knight.incrementcollcount();
            e.getReportingBody().destroy();
            try {
                Collectiblesound = new SoundClip("Collect.wav");   // Open an audio input stream
                Collectiblesound.play();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ea) {
                System.out.println(ea);
            }
        }
    }
}
