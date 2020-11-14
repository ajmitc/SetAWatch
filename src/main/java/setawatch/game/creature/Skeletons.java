package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Skeletons extends Creature{

    public Skeletons(){
        this(8, "Enemy (25).jpg");
    }

    public Skeletons(int health, String imageFilename){
        super("Skeletons", CreatureType.UNDEAD, 1, health, imageFilename);
    }

    @Override
    public void onEndOfRound(Game game, View view) {
        super.onEndOfRound(game, view);
        // If skeletons are on top of the graveyard at the end of a round, put them at the start of the line next round
        if (game.getGraveyard().peek() == this){
            game.getGraveyard().getDeck().remove(this);
            game.getCreatureDeck().addToTop(this);
        }
    }
}
