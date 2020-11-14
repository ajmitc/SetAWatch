package setawatch.game.adventurer.wizard;

import setawatch.game.DiceType;
import setawatch.game.Game;
import setawatch.game.adventurer.Adventurer;

import java.util.Collections;

public class Wizard extends Adventurer {

    public Wizard(){
        super("Wizard", true, DiceType.SIX_SIDED, "Wizard.jpg");
        // Add AdventurerAbility options
        getAllHeroAbilities().add(new IlluminateAbility());
        getAllHeroAbilities().add(new ChaosBlastAbility());
        getAllHeroAbilities().add(new PolymorphAbility());
        getAllHeroAbilities().add(new ShockboltAbility());
        getAllHeroAbilities().add(new CurseAbility());
        Collections.shuffle(allHeroAbilities);

        getAssignedHeroAbilities().add(getAllHeroAbilities().get(0));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(1));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(2));
        getAssignedHeroAbilities().get(2).setExhausted(true);
    }

    @Override
    public void activateHeroSpecialAbility(Game game) {
        // TODO At camp, draw the top 4 cards from the unused location deck and pick a location card to swap with the
        //  top card of the map deck.  Return the remaining cards to the bottom of the usused location deck.
    }
}
