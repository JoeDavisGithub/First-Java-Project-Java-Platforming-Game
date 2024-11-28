package DoorInteract;

import PlayerInteract.PlayChar;
import city.cs.engine.*;
import MainGame.Game;
//this is used to prevent the player from using the door before they have met the level requirements.
public class DoorListener implements CollisionListener{
    private Game game;
    public DoorListener(Game game){this.game = game;}
    //upon collision it will check the player's collectible count and check if it is enough to progress to the next level
    @Override
    public void collide(CollisionEvent e){
        PlayChar knight = game.getPlayer();
        if (e.getOtherBody()==knight && game.isCurrentLevelCompleted()){
            //in the event of an error with level progression, using system outputs is useful to identify
            //where the program stopped working.
            System.out.println("Going to the next level");
            //the next level is loaded through the current game, as that is where variables such as the current level
            //and methods necessary to repopulate the world are stored.
            game.goNextLevel();
        }
    }

}
