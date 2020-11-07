package setawatch.game.creature;

import setawatch.game.Game;

public class FellBeast extends Creature{

    public FellBeast(){
        super("Fell Beast", CreatureType.FIRE_DEMON, 2, 10, "Enemy (15).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // TODO Draw another creature and place it in front of Fell Beast (activate only once)
    }
}
