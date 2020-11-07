package setawatch.game.adventurer.cleric;

import setawatch.game.DiceType;
import setawatch.game.Game;
import setawatch.game.adventurer.Adventurer;

import java.util.Collections;

public class Cleric extends Adventurer {

    public Cleric(){
        super("Cleric", true, DiceType.SIX_SIDED, "Cleric.jpg");
        // Add AdventurerAbility options
        getAllHeroAbilities().add(new Banish());
        getAllHeroAbilities().add(new BlindingLight());
        getAllHeroAbilities().add(new CleanseUndead());
        getAllHeroAbilities().add(new ProtectiveWard());
        getAllHeroAbilities().add(new Rebuke());
        Collections.shuffle(allHeroAbilities);

        getAssignedHeroAbilities().add(getAllHeroAbilities().get(0));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(1));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(2));
        getAssignedHeroAbilities().get(2).setExhausted(true);
    }

    @Override
    public void activateHeroSpecialAbility(Game game) {
        // TODO At camp, refresh 1 exhausted adventurer card with any die that is a 4+
    }
}
