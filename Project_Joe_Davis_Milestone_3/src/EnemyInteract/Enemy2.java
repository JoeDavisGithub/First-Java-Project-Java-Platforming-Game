package EnemyInteract;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;

//this is the flyer type enemy.
public class Enemy2 extends Walker {
    //they all use a custom polygon
    private static final Shape shape = new PolygonShape(
            -0.183f,-0.391f, -0.287f,-0.084f, -0.287f,0.208f, -0.134f,0.381f, 0.203f,0.381f, 0.312f,0.213f, 0.312f,-0.084f, 0.233f,-0.391f);
    private static final BodyImage image =
            new BodyImage("flyer.gif",2.0f);
    private static String[] imglist = {"flyerflip.gif","flyer.gif"};
    private int health;
    //these enemies are faster horizontally than they are vertically.
    private float flightspeed = -5;
    private float UpDown = 2;

    public Enemy2(World world){
        super(world,shape);
        addImage(image);
        this.health = 1;
        //by setting their gravity to 0, they are able to fly
        this.setGravityScale(0);
        this.startWalking(flightspeed);
        //however they need an initial velocity to begin moving up and down
        this.setLinearVelocity(new Vec2(0, this.UpDown));
    }
    //getters and setters to change the vertical and horizontal velocity of the enemy
    public float getupdown(){return this.UpDown;}
    public void setupdown(float updown){this.UpDown = updown;}
    public float getflightspeed(){return flightspeed;}
    public void setflightspeed(){this.flightspeed = -(this.flightspeed); }
    //function to change the direction the enemy faces via separate images.
    public String changeimage(){
        System.out.println("Change Image");
        if(this.getflightspeed()> 0 ){
            return imglist[0];
        }
        else{
            return imglist[1];
        }
    }




}
