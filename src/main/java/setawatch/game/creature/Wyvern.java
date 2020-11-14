package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Wyvern extends Creature{

    public Wyvern(){
        super("Wyvern", CreatureType.DRAGON, 2, 6, "Enemy (19).jpg");
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // -3 firewood
        game.adjFirewood(-3);
    }
}
