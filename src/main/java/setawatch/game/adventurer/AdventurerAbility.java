package setawatch.game.adventurer;

import setawatch.game.Game;
import setawatch.view.ImageUtil;

import java.awt.*;

public abstract class AdventurerAbility {
    public static final int HERO_ABILITY_CARD_WIDTH = 200;

    protected String name;
    protected boolean passive;
    protected Image imageActive;
    protected Image imageExhausted;
    protected boolean exhausted;

    public AdventurerAbility(String name, String imageFilename, String imageExhaustedFilename){
        this.name = name;
        this.imageActive = ImageUtil.get(imageFilename, HERO_ABILITY_CARD_WIDTH);
        this.imageExhausted = ImageUtil.get(imageExhaustedFilename, HERO_ABILITY_CARD_WIDTH);
        this.exhausted = false;
    }

    /**
     * Activate this ability
     * @param game
     */
    public abstract void activate(Game game);

    public String getName() {
        return name;
    }

    public boolean isPassive() {
        return passive;
    }

    public Image getImageActive() {
        return imageActive;
    }

    public Image getImageExhausted() {
        return imageExhausted;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void setExhausted(boolean exhausted) {
        this.exhausted = exhausted;
    }
}
