package setawatch.view;

import setawatch.Model;
import setawatch.game.Game;

import javax.swing.*;
import java.awt.*;

public class View {
    private static final String MAINMENU = "Mainmenu";
    private static final String GAME = "game";

    private Model model;
    private JFrame frame;

    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;

    public View(Model model){
        this.model = model;
        this.frame = new JFrame();

        this.frame.setTitle("Set A Watch");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setLocation(0, 0);
        this.frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        mainMenuPanel = new MainMenuPanel(model, this);
        gamePanel = new GamePanel(model, this);

        this.frame.getContentPane().setLayout(new CardLayout());
        this.frame.getContentPane().add(MAINMENU, mainMenuPanel);
        this.frame.getContentPane().add(GAME, gamePanel);
    }

    public void showGame(){
        ((CardLayout) this.frame.getContentPane().getLayout()).show(this.frame.getContentPane(), GAME);
    }

    public void showMainMenu(){
        ((CardLayout) this.frame.getContentPane().getLayout()).show(this.frame.getContentPane(), MAINMENU);
    }

    public void refresh(){
        gamePanel.refresh();
    }

    public void initGame(){
        gamePanel.initGame();
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public JFrame getFrame() {
        return frame;
    }
}
