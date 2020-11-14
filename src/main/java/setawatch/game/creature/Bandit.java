package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.game.PhaseStep;
import setawatch.view.View;

public class Bandit extends Creature{

    public Bandit(){
        super("Bandit", CreatureType.HUMAN, 1, 7, "Enemy (21).jpg");
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // bandit steals one available dice of the groups choosing
        game.setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES_STEAL_DIE);
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        if (getStolenDice() != null){
            setStolenDice(null);
        }
    }
}
