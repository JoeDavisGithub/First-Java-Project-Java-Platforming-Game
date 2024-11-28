package CollectibleInteract;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.Color;
//This is the collectible of the game
public class Collectible extends DynamicBody {
    //all use a standard small design
    private static final Shape shape = new BoxShape(0.2f,0.2f);
    //when initialised they are created in the current world with the static class shape, and set to the colour orange
    public Collectible(World world) {
        super(world, shape);
        setFillColor(Color.orange);
    }


}

