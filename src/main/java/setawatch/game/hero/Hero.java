package setawatch.game.hero;

import setawatch.game.Dice;
import setawatch.game.DiceType;
import setawatch.game.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    protected String name;
    protected Image image;
    protected boolean ranged;
    protected DiceType diceType;
    protected List<HeroAbility> assignedHeroAbilities = new ArrayList<>(3);
    protected List<HeroAbility> allHeroAbilities = new ArrayList<>();
    protected List<Dice> dice = new ArrayList<>(3);

    public Hero(String name, boolean ranged, DiceType diceType, String imageFilename){
        this.name = name;
        this.ranged = ranged;
        this.diceType = diceType;

        dice.add(new Dice(diceType));
        dice.add(new Dice(diceType));
        dice.add(new Dice(diceType));
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

    public List<HeroAbility> getAssignedHeroAbilities() {
        return assignedHeroAbilities;
    }

    public List<HeroAbility> getAllHeroAbilities() {
        return allHeroAbilities;
    }

    public List<Dice> getDice() {
        return dice;
    }
}
