package setawatch.game;

import setawatch.game.adventurer.Adventurer;
import setawatch.view.ImageUtil;

import java.awt.*;
import java.util.Date;
import java.util.Random;

public class Dice {
    private static final Random GEN = new Random(new Date().getTime());
    public static final int DICE_SIZE = 45;

    private static final Image[] EIGHT_SIDE_IMAGES = {
            ImageUtil.get("1.png", DICE_SIZE),
            ImageUtil.get("2.png", DICE_SIZE),
            ImageUtil.get("3.png", DICE_SIZE),
            ImageUtil.get("4.png", DICE_SIZE),
            ImageUtil.get("5.png", DICE_SIZE),
            ImageUtil.get("6.png", DICE_SIZE),
            ImageUtil.get("7.png", DICE_SIZE),
            ImageUtil.get("8.png", DICE_SIZE)
    };
    private static final Image[] SIX_SIDE_IMAGES = {
            ImageUtil.get("Red (1).png", DICE_SIZE),
            ImageUtil.get("Red (2).png", DICE_SIZE),
            ImageUtil.get("Red (3).png", DICE_SIZE),
            ImageUtil.get("Red (4).png", DICE_SIZE),
            ImageUtil.get("Red (5).png", DICE_SIZE),
            ImageUtil.get("Red (6).png", DICE_SIZE)
    };

    private Adventurer adventurer;
    private DiceType type;
    private int value;
    private boolean assigned = false;

    private int px, py;

    public Dice(Adventurer adventurer, DiceType type){
        this.adventurer = adventurer;
        this.type = type;
    }

    public Adventurer getAdventurer() {
        return adventurer;
    }

    public DiceType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void roll(){
        if (type == DiceType.SIX_SIDED)
            value = GEN.nextInt(6) + 1;
        else if (type == DiceType.EIGHT_SIDED)
            value = GEN.nextInt(8) + 1;
        // TODO Remove this.  This is to test the Check map action
        value = 4;
    }

    public void setCoord(int x, int y){
        this.px = x;
        this.py = y;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public Image getImage(){
        return type == DiceType.EIGHT_SIDED? EIGHT_SIDE_IMAGES[value - 1]: SIX_SIDE_IMAGES[value - 1];
    }
}
