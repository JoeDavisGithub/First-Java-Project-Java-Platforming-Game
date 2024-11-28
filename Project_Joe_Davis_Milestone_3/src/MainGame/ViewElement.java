package MainGame;
import Levels.GameLevel;
import PlayerInteract.PlayChar;
import city.cs.engine.*;
import javax.swing.*;
import java.awt.*;
//This is the window of the game and UI elements
public class ViewElement extends UserView{
    PlayChar knight;
    public void setPlayer(PlayChar knight){this.knight = knight;}
    private Image background;
    private Image hitpoints;
    private Image collectibles;

    public ViewElement(World world, PlayChar knight, int width, int height){
        super(world,width,height);
        this.knight = knight;
        //loads the images for the background, the hitpoints and collectibles.
        this.background = new ImageIcon("Level1Bg.png").getImage();
        this.hitpoints = new ImageIcon("hitpoints.png").getImage();
        this.collectibles = new ImageIcon("collect.png").getImage();
        //scales them all to fit in the window
        this.background.getScaledInstance(500,500,Image.SCALE_SMOOTH);
        this.hitpoints.getScaledInstance(60,60,Image.SCALE_SMOOTH);
        this.collectibles.getScaledInstance(10,  10,Image.SCALE_SMOOTH);
    }
    @Override
    protected void paintBackground(Graphics2D g){
        //draws the icons, the background at the centre of the view and the UI elements at the top left
        g.drawImage(((GameLevel)this.getWorld()).getBackgroundImage(),0,0,this);
        g.drawImage(hitpoints,10,10,null);
        g.drawImage(collectibles,22,60,null);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        //displays the information using a font that will stand out against the background
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.setColor(Color.BLUE);
        //displays it next to the icons.
        g.drawString(": "+knight.getHitpoints(),50,45);
        g.drawString(": "+knight.getCollcount(),50,80);
    }
}

