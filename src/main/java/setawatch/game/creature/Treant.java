package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Treant extends Creature{

    public Treant(){
        super("Treant", CreatureType.EARTH_ELEMENTAL, 1, 8, "Enemy (37).jpg"); // Try 38 too
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        // +2 firewood
        game.adjFirewood(2);
    }
}
