package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.game.PhaseStep;
import setawatch.view.View;

public class EmberDrake extends Creature{

    public EmberDrake(){
        this(11, "Enemy (11).jpg");
    }

    public EmberDrake(int health, String imageFilename){
        super("Ember Drake", CreatureType.DRAGON, 2, health, imageFilename);
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // Exhaust 2 Adventurer Ability cards and -3 firewood
        game.setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES_EXHAUST_ADVENTURER_ABILITY);
        game.setNumAdventurerAbilityCardsToExhaust(2);
        game.adjFirewood(-3);
    }
}
