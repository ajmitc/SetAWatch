package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.game.PhaseStep;
import setawatch.view.View;

public class Minotaur extends Creature{

    public Minotaur(){
        super("Minotaur", CreatureType.UNHALLOWED, 2, 10, "Unhallowed (12).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Move Minotaur to first position
        game.getLine().remove(this);
        game.getLine().add(0, this);
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // Exhaust an Adventurer Ability card
        game.setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES_EXHAUST_ADVENTURER_ABILITY);
        game.setNumAdventurerAbilityCardsToExhaust(1);
    }
}
