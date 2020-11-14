package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Wyvern2 extends Creature{

    public Wyvern2(){
        super("Wyvern", CreatureType.DRAGON, 2, 7, "Enemy (20).jpg");
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // -3 firewood
        game.adjFirewood(-3);
    }
}
