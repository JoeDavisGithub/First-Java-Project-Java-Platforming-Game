package FileManipulation;

import Levels.GameLevel;
import Levels.Platform;
import PlayerInteract.PlayChar;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import java.io.FileWriter;
import java.io.IOException;

/**
 * GameSaver saves the current state of the game into a text file
 */
public class GameSaver {
    /**
     * fileName is a String that the file will be saved as
     */
    private String fileName;

    /**
     * GameSaver's constructor is used to set the filename
     * @param fileName
     */
    public GameSaver(String fileName) {
        this.fileName = fileName;
    }

    /**
     * <p>saveGame() is used to save all of the bodies present in the GameLevel including their details such as
     * their Vec2 position, or in the player's case their hitpoints and collectibles. for staticbodies it also gets their width and
     * height so that when loaded they can be initialised with the correct dimensions</p>
     * @param gameWorld enters the current world the user is in to be accessed by the .getDynamicBodies or .getStaticBodies methods
     * @throws IOException
     */
    public void saveGame(GameLevel gameWorld) throws IOException {
        boolean append = true;
        /**
         * writer is used to write to the file
         */
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);

            writer.write(gameWorld.getLevelNumber()+"\n");
            writer.write(gameWorld.getPlayer().getPosition().x + ","+gameWorld.getPlayer().getPosition().y + ","
                    +gameWorld.getPlayer().getCollcount()+ "\n");

            writer.write(gameWorld.getPlayer().getClass().getSimpleName()+","+gameWorld.getPlayer().getPosition().x
                    +","+gameWorld.getPlayer().getPosition().y+","+gameWorld.getPlayer().getCollcount()+","+
                    gameWorld.getPlayer().getHitpoints()+"\n");

            for(DynamicBody body: gameWorld.getDynamicBodies()){
                if(!(body instanceof PlayChar)) {
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
             }
            for(StaticBody body: gameWorld.getStaticBodies()){
                if (body instanceof Platform){
                    writer.write(body.getClass().getSimpleName()+","+ body.getPosition().x+","+body.getPosition().y+
                            ","+((Platform) body).getWidth()+","+((Platform) body).getHeight()+"\n");
                } else{
                    writer.write(body.getClass().getSimpleName()+","+body.getPosition().x+","+body.getPosition().y+"\n");
                }
                }

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
