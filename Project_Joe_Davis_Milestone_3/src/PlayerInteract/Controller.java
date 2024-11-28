package PlayerInteract;

import FileManipulation.GameLoader;
import FileManipulation.GameSaver;
import Levels.GameLevel;
import MainGame.Game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

//how the user will be able to play the game, certain key inputs will move the player body or perform certain actions
public class Controller extends KeyAdapter {
    private static final float Jumpspeed = 10;
    private static final float Walkspeed = 9;

    private Walker body;
    private GameLevel currentlevel;
    private Game game;
    public Controller(Walker body, GameLevel level,Game g){this.body = body;
    this.body2 = body;
    currentlevel=level;
    game = g;}
    private static Walker body2;
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // if the user presses x the game exits.
        if (code == KeyEvent.VK_X) { // X = quit
            System.exit(0);
        }
        //if the user presses W the player jumps, but only if the player is not currently in the air
        else if (code == KeyEvent.VK_W) { // J = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                body.jump(Jumpspeed);
            }
        } //allows the player to move left and right using A and D, as well as giving the player a dash using E and Q
        // if the player is currently on the ground.
        else if (code == KeyEvent.VK_A) {
            body.startWalking(-Walkspeed); // 1 = walk left
            body.removeAllImages();
            body.addImage(new BodyImage("ShovelKnightFlip.gif",4.0f));
        } else if (code == KeyEvent.VK_D) {
            body.startWalking(Walkspeed); // 2 = walk right
            body.removeAllImages();
            body.addImage(new BodyImage("ShovelKnight.gif",4.0f));
        } else if (code == KeyEvent.VK_Q) {
            Vec2 v = body.getLinearVelocity();
            if (Math.abs(v.y) < 0.01f) {
                body.startWalking(-Walkspeed * 3); // 1 = walk left
                body.removeAllImages();
                body.addImage(new BodyImage("ShovelKnightFlip.gif", 4.0f));
            }
        } else if (code == KeyEvent.VK_E) {
            Vec2 v = body.getLinearVelocity();
            if (Math.abs(v.y) < 0.01f) {
                body.startWalking(Walkspeed * 3); // 2 = walk right
                body.removeAllImages();
                body.addImage(new BodyImage("ShovelKnight.gif", 4.0f));
            }
        } else if (code == KeyEvent.VK_J){
            GameSaver save = new GameSaver("save.txt");
            try {
                save.saveGame(currentlevel);
            } catch(IOException ex){
                ex.printStackTrace();
            }
        } else if (code == KeyEvent.VK_K){
            GameLoader sr = new GameLoader("save.txt",game);
            try{

                GameLevel loadedGame = sr.LoadGame();
                game.goToLevel(loadedGame);
            } catch(IOException ex){
                ex.printStackTrace();
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        Vec2 v = body.getLinearVelocity();
        //while the image of the player was changed while the button was pressed to give the appearance of running
        //when it is released the player needs to halt, and change its image to a stationary appearance.
        if (code == KeyEvent.VK_A) {
            body.stopWalking();
            body.setLinearVelocity(new Vec2(0,v.y));
            body.removeAllImages();
            body.addImage(new BodyImage("ShovelResizeFlip.png",2.0f));
        } else if (code == KeyEvent.VK_D) {
            body.stopWalking();
            body.setLinearVelocity(new Vec2(0,v.y));
            body.removeAllImages();
            body.addImage(new BodyImage("ShovelResize.png",2.0f));
        } else if (code == KeyEvent.VK_Q) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(new BodyImage("ShovelResizeFlip.png",2.0f));
        } else if (code == KeyEvent.VK_E) {
            body.stopWalking();
            body.removeAllImages();
            body.addImage(new BodyImage("ShovelResize.png",2.0f));
        }
    }
    //when the player is attacked, it will cause the player to rebound by a small amount
    public static void attacked(){
        Vec2 v = body2.getLinearVelocity();
        float vx2 = 0;
        if(v.x<0){
            vx2 = v.x-3;
        }
        else if(v.x>0){
            vx2 = v.x+3;
        }
        body2.setLinearVelocity(new Vec2(vx2,-(v.y-6)));
    }

    public void setBody(Walker body) {
        this.body = body;
    }
    public void setWorld(GameLevel level){this.currentlevel = level;}

}
