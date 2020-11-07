package setawatch.game.creature;

import setawatch.game.Game;

public class FellBeast2 extends Creature{

    public FellBeast2(){
        super("Fell Beast", CreatureType.FIRE_DEMON, 2, 11, "Enemy (16).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // TODO Draw another creature and place it in front of Fell Beast (activate only once)
    }
}
