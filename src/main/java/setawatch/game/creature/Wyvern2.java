package setawatch.game.creature;

import setawatch.game.Game;

public class Wyvern2 extends Creature{

    public Wyvern2(){
        super("Wyvern", CreatureType.DRAGON, 2, 7, "Enemy (20).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // -3 firewood
        game.adjFirewood(-3);
    }
}
