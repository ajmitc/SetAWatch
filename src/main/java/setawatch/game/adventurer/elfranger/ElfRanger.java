package setawatch.game.adventurer.elfranger;

import setawatch.game.DiceType;
import setawatch.game.Game;
import setawatch.game.adventurer.Adventurer;

import java.util.Collections;

public class ElfRanger extends Adventurer {

    public ElfRanger(){
        super("Elf Ranger", true, DiceType.EIGHT_SIDED, "Elf Ranger.jpg");
        // Add AdventurerAbility options
        getAllHeroAbilities().add(new ElfSensesAbility());
        getAllHeroAbilities().add(new LongShotAbility());
        getAllHeroAbilities().add(new FarSightAbility());
        getAllHeroAbilities().add(new SlumberAbility());
        getAllHeroAbilities().add(new SharpshooterAbility());
        Collections.shuffle(allHeroAbilities);

        getAssignedHeroAbilities().add(getAllHeroAbilities().get(0));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(1));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(2));
        getAssignedHeroAbilities().get(0).setExhausted(true);
    }

    @Override
    public void activateHeroSpecialAbility(Game game) {
        // TODO At camp, draw, look at, and rearrange the top 4 cards of the creature deck and return them to the top of the creature deck
    }
}
