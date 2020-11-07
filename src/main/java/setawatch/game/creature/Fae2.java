package setawatch.game.creature;

import setawatch.game.Game;

public class Fae2 extends Creature{

    public Fae2(){
        super("Fae", CreatureType.FAIRY, 1, 6, "Enemy (10).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // -1 firewood
        game.adjFirewood(-1);
    }
}
