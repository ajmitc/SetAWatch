package setawatch.game.location;

import setawatch.game.Game;
import setawatch.view.ImageUtil;

import java.awt.*;

public abstract class Location {
    protected Image image;
    protected String name;
    protected boolean respite;
    protected boolean finalLocation;
    protected int firewoodChange;
    protected int lineSize;

    public Location(String name, boolean respite, int firewoodChange, int lineSize, String imageFilename){
        this.name = name;
        this.respite = respite;
        this.firewoodChange = firewoodChange;
        this.lineSize = lineSize;
        this.finalLocation = false;
        this.image = ImageUtil.get(imageFilename, 400);
    }

    public abstract void makeCamp(Game game);

    public abstract void leaveCamp(Game game);

    public String getName() {
        return name;
    }

    public boolean isRespite() {
        return respite;
    }

    public int getFirewoodChange() {
        return firewoodChange;
    }

    public int getLineSize() {
        return lineSize;
    }

    public boolean isFinalLocation() {
        return finalLocation;
    }

    public Image getImage() {
        return image;
    }
}
