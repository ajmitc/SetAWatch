package setawatch.game.creature;

import setawatch.game.Game;

public class Fae extends Creature{

    public Fae(){
        super("Fae", CreatureType.FAIRY, 1, 5, "Enemy (9).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // -1 firewood
        game.adjFirewood(-1);
    }
}
