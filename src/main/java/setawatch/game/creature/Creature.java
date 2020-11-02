package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.ImageUtil;

import java.awt.*;

public abstract class Creature {
    public static final int CREATURE_CARD_WIDTH = 200;
    protected Image image;
    protected String name;
    protected CreatureType type;
    protected int health;
    protected int attack;

    protected boolean revealed = false;

    public Creature(String name, CreatureType type, int attack, int health, String imageFilename){
        this.name = name;
        this.type = type;
        this.health = health;
        this.attack = attack;
        this.image = ImageUtil.get(imageFilename, CREATURE_CARD_WIDTH);
        this.revealed = false;
    }

    public void onReveal(Game game){}

    public void onDefeat(Game game){}

    public void onGoing(Game game){}

    public String getName() {
        return name;
    }

    public CreatureType getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public Image getImage() {
        return image;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isRevealed() {
        return revealed;
    }
}
