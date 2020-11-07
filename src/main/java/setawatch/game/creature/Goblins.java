package setawatch.game.creature;

import setawatch.game.Game;

public class Goblins extends Creature{

    public Goblins(){
        super("Goblins", CreatureType.GOBLIN, 1, 6, "Enemy (17).jpg");
    }

    @Override
    public void onReveal(Game game) {
        super.onReveal(game);
        // TODO Move to first position
    }
}
