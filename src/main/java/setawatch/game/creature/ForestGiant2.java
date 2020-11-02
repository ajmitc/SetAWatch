package setawatch.game.creature;

import setawatch.game.Game;

public class ForestGiant2 extends Creature{

    public ForestGiant2(){
        super("Forest Giant", CreatureType.GIANT, 2, 8, "Enemy (2).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO Giant's health is always combined with the creature's health behind it.  Reveal next creature in line if necessary.
        //health = 8 + 0;
    }
}
