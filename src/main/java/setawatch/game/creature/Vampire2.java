package setawatch.game.creature;

import setawatch.game.Game;

public class Vampire2 extends Creature{

    public Vampire2(){
        super("Vampire", CreatureType.UNDEAD, 1, 1, "Enemy (28).jpg");
    }

    @Override
    public void onReveal(Game game) {
        super.onReveal(game);
        // TODO Steal the highest available die to become this card's health value
    }
}
