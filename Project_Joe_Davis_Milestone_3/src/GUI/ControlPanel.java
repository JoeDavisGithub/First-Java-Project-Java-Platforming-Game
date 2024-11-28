package GUI;

import MainGame.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// the GUI was designed using the javax swing builder
public class ControlPanel extends JPanel {
    private JPanel menu;
    private Game game;

    private JButton pauseButton;
    private JButton resumeButton;
    private JButton quitButton;
    private JButton nextLvlButton;
    private JButton restartGameButton;

    public JPanel getMenu(){return menu;}
    //implements the correct methods to each button.
    public ControlPanel(Game game){
        this.game = game;
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { game.Pause();}
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { game.Resume();}
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { game.Quit();}
        });
        nextLvlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { game.advanceLevel();}
        });
        restartGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { game.Restart();}
        });




    }
}
