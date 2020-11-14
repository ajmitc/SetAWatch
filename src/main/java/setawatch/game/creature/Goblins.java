package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Goblins extends Creature{

    public Goblins(){
        this(6, "Enemy (17).jpg");
    }

    public Goblins(int health, String imageFilename){
        super("Goblins", CreatureType.GOBLIN, 1, health, imageFilename);
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Move to first position
        game.getLine().remove(this);
        game.getLine().add(0, this);
    }
}
