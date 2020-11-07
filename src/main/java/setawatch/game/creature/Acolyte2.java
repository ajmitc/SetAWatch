package setawatch.game.creature;

import setawatch.game.Game;

public class Acolyte2 extends Creature{

    public Acolyte2(){
        super("Acolyte", CreatureType.HUMAN, 1, 7, "Enemy (14).jpg");
    }

    @Override
    public void onReveal(Game game) {
        super.onReveal(game);
        // TODO Draw a creature and add it to the horde
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // TODO Reshuffle a Summon card back into the creature deck, if there is one in the graveyard
    }
}
