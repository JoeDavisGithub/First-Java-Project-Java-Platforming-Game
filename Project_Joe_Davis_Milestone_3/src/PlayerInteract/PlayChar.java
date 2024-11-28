package PlayerInteract;

import city.cs.engine.*;


public class PlayChar extends Walker {
    //the player has a unique polygon to fit the image
    private static final Shape shape = new PolygonShape(
            0.613f,0.724f, 0.321f,-0.944f, -0.487f,-0.94f, -0.711f,0.72f);
    private static final BodyImage image =
            new BodyImage("ShovelResize.png",2.0f);
    private int collcount;
    private int hitpoints;


    public PlayChar(World world){
        super(world,shape);
        addImage(image);
        //when declared the collectible count starts at 0, and the hitpoints are set to 5
        collcount = 0;
        hitpoints = 5;

    }

    public void setcollcount(int carry){collcount= carry;}
    public void incrementcollcount(){
        collcount++;
        System.out.println("Collectibles: "+collcount);
    }
    public void resetcollcount(){ collcount=0; }
    //this decrements the players hitpoints depending on the strength of the attacker
    public void AttackPlayer(int i){
        this.hitpoints = this.hitpoints -i;
        System.out.println("Player Attacked, HP: "+this.hitpoints);
    }
    public int getHitpoints(){return this.hitpoints;}
    public void setHitpoints(int hit) {this.hitpoints = hit;}
    public int getCollcount() { return this.collcount;}
}
