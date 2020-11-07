package setawatch.game.creature;

import setawatch.game.Game;

public class Wyvern extends Creature{

    public Wyvern(){
        super("Wyvern", CreatureType.DRAGON, 2, 6, "Enemy (19).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // -3 firewood
        game.adjFirewood(-3);
    }
}
