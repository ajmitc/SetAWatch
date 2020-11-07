package setawatch.game.creature;

import setawatch.game.Game;

public class Vampire extends Creature{

    public Vampire(){
        super("Vampire", CreatureType.UNDEAD, 1, 1, "Enemy (27).jpg");
    }

    @Override
    public void onReveal(Game game) {
        super.onReveal(game);
        // TODO Steal the highest available die to become this card's health value
    }
}
