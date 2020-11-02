package setawatch.game.creature;

import setawatch.game.Game;

public class ForestGiant extends Creature{

    public ForestGiant(){
        super("Forest Giant", CreatureType.GIANT, 2, 7, "Enemy (1).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO Giant's health is always combined with the creature's health behind it.  Reveal next creature in line if necessary.
        //health = 7 + 0;
    }
}
