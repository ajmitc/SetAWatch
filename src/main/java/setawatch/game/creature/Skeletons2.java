package setawatch.game.creature;

import setawatch.game.Game;

public class Skeletons2 extends Creature{

    public Skeletons2(){
        super("Skeletons", CreatureType.UNDEAD, 1, 9, "Enemy (26).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO If skeletons are on top of the graveyard at the end of a round, put them at the start of the line next round
    }
}
