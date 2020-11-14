package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Warlord extends Creature{

    public Warlord(){
        super("Warlord", CreatureType.UNHALLOWED, 3, 11, "Unhallowed (2).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        // All other creatures in line have +2 health
        for (Creature creature: game.getLine()) {
            if (creature != this)
                creature.setHealthBonus(2);
        }
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        for (Creature creature: game.getLine()) {
            if (creature != this)
                creature.setHealthBonus(2);
        }
    }
}
