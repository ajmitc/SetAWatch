package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.game.PhaseStep;
import setawatch.view.View;

public class WraithLord extends Creature{

    public WraithLord(){
        super("Wraith Lord", CreatureType.UNHALLOWED, 2, 12, "Unhallowed (4).jpg");
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        // Exhaust an Adventurer Ability Card
        game.setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES_EXHAUST_ADVENTURER_ABILITY);
        game.setNumAdventurerAbilityCardsToExhaust(1);
    }
}
