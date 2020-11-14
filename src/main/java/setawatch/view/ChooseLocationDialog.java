package setawatch.view;

import setawatch.game.location.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseLocationDialog extends JDialog {
    private Location location1;
    private Location location2;
    private Location selected;

    public ChooseLocationDialog(JFrame parent, Location location1, Location location2){
        super(parent, "Choose Location", true);
        this.location1 = location1;
        this.location2 = location2;

        selected = this.location1;
        setSize(new Dimension(location1.getImage().getWidth(null) * 2, location1.getImage().getHeight(null) + 30));
        setLocationRelativeTo(null);

        JPanel cardPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics graphics){
                graphics.drawImage(location1.getImage(), 0, 0, null);
                graphics.drawImage(location2.getImage(), location1.getImage().getWidth(null), 0, null);

                graphics.setColor(Color.RED);
                if (selected == location1)
                    graphics.drawRect(0, 0, location1.getImage().getWidth(null), location1.getImage().getHeight(null));
                else
                    graphics.drawRect(location1.getImage().getWidth(null), 0, location2.getImage().getWidth(null), location2.getImage().getHeight(null));
            }
        };

        cardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getX() > location1.getImage().getWidth(null))
                    selected = location2;
                else
                    selected = location1;
                cardPanel.repaint();
                if (e.getClickCount() >= 2){
                    setVisible(false);
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cardPanel, BorderLayout.CENTER);
    }

    public Location getSelected(){ return selected; }
}
