package setawatch.view;

import setawatch.Model;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Model model;
    private View view;

    private CampPanel campPanel;
    private AdventurerPanel adventurerPanel;

    public GamePanel(Model model, View view){
        super(new BorderLayout());
        this.model = model;
        this.view = view;

        campPanel = new CampPanel(model, view);
        adventurerPanel = new AdventurerPanel(model, view);

        add(campPanel, BorderLayout.CENTER);
        add(adventurerPanel, BorderLayout.EAST);
    }

    public void init(){
        adventurerPanel.init();
    }

    public void refresh(){
        campPanel.refresh();
        adventurerPanel.refresh();
    }
}
