package setawatch.game;

import setawatch.view.ImageUtil;

import java.awt.*;
import java.util.Date;
import java.util.Random;

public class Dice {
    private static final Random GEN = new Random(new Date().getTime());
    private static final Image[] SIDE_IMAGES = {
            ImageUtil.get("1.png"),
            ImageUtil.get("2.png"),
            ImageUtil.get("3.png"),
            ImageUtil.get("4.png"),
            ImageUtil.get("5.png"),
            ImageUtil.get("6.png"),
            ImageUtil.get("7.png"),
            ImageUtil.get("8.png")
    };

    private DiceType type;
    private int value;

    private int px, py;

    public Dice(DiceType type){
        this.type = type;
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

    public Image getImage(){
        return SIDE_IMAGES[value - 1];
    }
}
