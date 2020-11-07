package setawatch.game.creature;

import setawatch.game.Game;

public class Skeletons extends Creature{

    public Skeletons(){
        super("Skeletons", CreatureType.UNDEAD, 1, 8, "Enemy (25).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO If skeletons are on top of the graveyard at the end of a round, put them at the start of the line next round
    }
}
