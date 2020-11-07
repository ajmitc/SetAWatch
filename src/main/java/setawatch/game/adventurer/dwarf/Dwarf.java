package setawatch.game.adventurer.dwarf;

import setawatch.game.DiceType;
import setawatch.game.Game;
import setawatch.game.adventurer.Adventurer;

import java.util.Collections;

public class Dwarf extends Adventurer {

    public Dwarf(){
        super("Dwarf", false, DiceType.EIGHT_SIDED, "Dwarf.jpg");
        // Add AdventurerAbility options
        getAllHeroAbilities().add(new BeserkerAbility());
        getAllHeroAbilities().add(new CleaveAbility());
        getAllHeroAbilities().add(new GiantsBaneAbility());
        getAllHeroAbilities().add(new ThrowAxeAbility());
        getAllHeroAbilities().add(new WoundAbility());
        Collections.shuffle(allHeroAbilities);

        getAssignedHeroAbilities().add(getAllHeroAbilities().get(0));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(1));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(2));
        getAssignedHeroAbilities().get(2).setExhausted(true);
    }

    @Override
    public void activateHeroSpecialAbility(Game game) {
        // TODO Increase firewood by 4
    }
}
