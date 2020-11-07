package setawatch.game.creature;

import setawatch.game.Game;

public class Goblins2 extends Creature{

    public Goblins2(){
        super("Goblins", CreatureType.GOBLIN, 1, 7, "Enemy (18).jpg");
    }

    @Override
    public void onReveal(Game game) {
        super.onReveal(game);
        // TODO Move to first position
    }
}
