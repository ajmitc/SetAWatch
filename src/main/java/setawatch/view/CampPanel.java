package setawatch.view;

import setawatch.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CampPanel extends JPanel {
    private static final int BOARD_WIDTH = 1000;

    private static final Point[] FIREWOOD_COORDS = {
            new Point(480, 438),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(540, 735),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0),
            new Point(0, 0)
    };

    private static final Point locationBackCoord = new Point(90, 103);
    private static final Point currentLocationCoord = new Point(514, 100);

    private Model model;
    private View view;

    private Image boardImage;
    private Image firewoodImage;
    private Image locationBack;

    private int mx, my;

    public CampPanel(Model model, View view){
        super(new BorderLayout());
        this.model = model;
        this.view = view;

        boardImage    = ImageUtil.get("Main Board.png", BOARD_WIDTH);
        firewoodImage = ImageUtil.get("Fire Token.png", 40);
        locationBack  = ImageUtil.get("Loc Back.jpg", 400);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mx = e.getX();
                my = e.getY();
                refresh();
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics){
        graphics.drawImage(boardImage, 0, 0, null);

        Point firewoodCoord = FIREWOOD_COORDS[model.getGame().getFirewood()];
        graphics.drawImage(firewoodImage, firewoodCoord.x, firewoodCoord.y, null);

        graphics.drawImage(locationBack, locationBackCoord.x, locationBackCoord.y, null);
        graphics.drawImage(model.getGame().getCurrentLocation().getImage(), currentLocationCoord.x, currentLocationCoord.y, null);

        graphics.setColor(Color.WHITE);
        graphics.drawString(mx + ", " + my, 50, 50);
    }

    public void refresh(){
        repaint();
    }
}
