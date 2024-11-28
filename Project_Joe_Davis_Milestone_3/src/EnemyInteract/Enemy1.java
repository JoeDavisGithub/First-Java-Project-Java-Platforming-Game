package EnemyInteract;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;

//this is the large slow walker type enemy
public class Enemy1 extends Walker {
    //this list is used for the program to change the body's current image depending on a value.
    private static String[] imglist = {"enemy1flip.gif","enemy1.gif"};
    //a custom polygon was created for the enemy to make the area of collision seem more realistic
    private static final Shape shape = new PolygonShape(
            -2.47f,0.33f, -2.47f,-1.83f, 2.28f,-1.77f, 2.28f,-0.64f);
    //they are initialised with the first index of the image list as they all spawn facing the same direction.
    private static final BodyImage image =
            new BodyImage(imglist[0],4.0f);
    //health to be implemented
    private int health;
    //the enemy walkspeeds for left and right movement
    private static float WalkspeedR = 3;
    private static float WalkspeedL = -3;
    // a value used to determine the enemy's current velocity
    private float walking;
    //a boolean value which will be used to determine which direction the enemy is walking based on it's velocity.
    private boolean directionLR;

    public Enemy1(World world){
        super(world,shape);
        addImage(image);
        this.health = 3;

    }

    public boolean getDirection(){return this.directionLR;}
    public float getWalking(){return this.walking;}
    //this is used to swap the current direction of the enemy to the opposite, it will be called in the collisionlistener
    //it changes the velocity of the enemy to the Walkspeed that is not equal to 'walking'.
    public float setSpeed(boolean direction){
        if(direction == false){
            this.walking = WalkspeedR;
            this.directionLR = true;
        }
        else if(direction == true){
            this.walking = WalkspeedL;
            this.directionLR = false;
        }
        //it then returns the new value of walking to be used as the walking velocity of the enemy.
        return walking;
    }
    //this function changes the enemy's image (which direction it faces in runtime) by using the 'walking' variable.
    //because walking is a velocity, negative values face left, and positive values face right.
    public String changeimage(){
        if(this.walking > 0 ){
            return imglist[1];
        }
        else{
            return imglist[0];
        }
    }


}
