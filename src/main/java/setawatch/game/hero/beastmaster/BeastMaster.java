package setawatch.game.hero.beastmaster;

import setawatch.game.DiceType;
import setawatch.game.Game;
import setawatch.game.hero.Hero;

import java.util.Collections;

public class BeastMaster extends Hero {

    public BeastMaster(){
        super("Beast Master", true, DiceType.EIGHT_SIDED, "Beast Master.jpg");
        // Add HeroAbility options
        getAllHeroAbilities().add(new TameBeastAbility());
        getAllHeroAbilities().add(new SetSnaresAbility());
        getAllHeroAbilities().add(new AnimalSentryAbility());
        getAllHeroAbilities().add(new HuntAbility());
        getAllHeroAbilities().add(new TrackingAbility());
        Collections.shuffle(allHeroAbilities);

        getAssignedHeroAbilities().add(getAllHeroAbilities().get(0));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(1));
        getAssignedHeroAbilities().add(getAllHeroAbilities().get(2));
        getAssignedHeroAbilities().get(2).setExhausted(true);
    }

    @Override
    public void activateHeroSpecialAbility(Game game) {
        // TODO At camp, assign this die as direct damage to the creature in first position as soon as it is revealed during the combat phase
    }
}
