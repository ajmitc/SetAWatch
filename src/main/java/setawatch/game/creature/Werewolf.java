package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Werewolf extends Creature{

    public Werewolf(){
        super("Werewolf", CreatureType.UNHALLOWED, 3, 11, "Unhallowed (6).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        // All forest creatures in the line have double their base health
        game.getLine().stream().filter(c -> c.getType() == CreatureType.FOREST_CREATURE).forEach(c -> c.setHealthMultiplier(2));
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        game.getLine().stream().filter(c -> c.getType() == CreatureType.FOREST_CREATURE).forEach(c -> c.setHealthMultiplier(1));
    }
}
