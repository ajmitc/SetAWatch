package setawatch.game.adventurer;

import setawatch.game.Dice;
import setawatch.game.DiceType;
import setawatch.game.Game;
import setawatch.game.creature.Creature;
import setawatch.view.ImageUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Adventurer {
    public static final int BOARD_WIDTH = 600;

    protected String name;
    protected Image image;
    protected boolean ranged;
    protected DiceType diceType;
    protected List<AdventurerAbility> assignedHeroAbilities = new ArrayList<>(3);
    protected List<AdventurerAbility> allHeroAbilities = new ArrayList<>();
    protected List<Dice> dice = new ArrayList<>(3);
    protected int numTimesAtCamp = 0;
    protected List<Creature> tamedCreatures = new ArrayList<>();

    protected boolean atCamp  = false;
    protected boolean atWatch = false;

    public Adventurer(String name, boolean ranged, DiceType diceType, String imageFilename){
        this.name = name;
        this.ranged = ranged;
        this.diceType = diceType;
        this.image = ImageUtil.get(imageFilename, BOARD_WIDTH);

        dice.add(new Dice(this, diceType));
        dice.add(new Dice(this, diceType));
        dice.add(new Dice(this, diceType));
    }

    public abstract void activateHeroSpecialAbility(Game game);

    public String getName() {
        return name;
    }

    public boolean isRanged() {
        return ranged;
    }

    public DiceType getDiceType() {
        return diceType;
    }

    public List<AdventurerAbility> getAssignedHeroAbilities() {
        return assignedHeroAbilities;
    }

    public List<AdventurerAbility> getAllHeroAbilities() {
        return allHeroAbilities;
    }

    public List<Dice> getDice() {
        return dice;
    }

    public Image getImage() {
        return image;
    }

    public int getNumTimesAtCamp() {
        return numTimesAtCamp;
    }

    public void setNumTimesAtCamp(int numTimesAtCamp) {
        this.numTimesAtCamp = numTimesAtCamp;
    }

    public void adjNumTimesAtCamp(int amount) {
        this.numTimesAtCamp += amount;
    }

    public boolean isAtCamp() {
        return atCamp;
    }

    public void setAtCamp(boolean atCamp) {
        this.atCamp = atCamp;
    }

    public boolean isAtWatch() {
        return atWatch;
    }

    public void setAtWatch(boolean atWatch) {
        this.atWatch = atWatch;
    }

    public List<Creature> getTamedCreatures() {
        return tamedCreatures;
    }
}
