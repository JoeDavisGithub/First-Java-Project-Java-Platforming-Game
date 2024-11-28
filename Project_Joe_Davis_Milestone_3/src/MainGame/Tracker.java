package MainGame;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
//the tracker moves the camera along with the player by detecting when the player 'steps'
public class Tracker implements StepListener{
    private WorldView view;

    private Body body;

    public Tracker(WorldView view,Body body){
        this.view = view;
        this.body = body;
    }
    @Override
    public void preStep(StepEvent e){
    }
    @Override
    //after every step it changes the centre of the view to the player's position.
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(body.getPosition()));
    }
    }


