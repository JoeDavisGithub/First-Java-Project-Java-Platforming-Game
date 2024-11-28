package DoorInteract;
import city.cs.engine.*;
//This is the "door" to the next level
public class Door extends StaticBody {
    //in the constructor, creates it in the current world with a uniform shape and image. all doors will appear the same.
    public Door(World world){
        super(world,new BoxShape(0.55f, 1.4f));
        addImage(new BodyImage("Portal.gif",2.8f));
    }

}
