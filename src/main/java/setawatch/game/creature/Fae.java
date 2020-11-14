package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Fae extends Creature{

    public Fae(){
        this(5, "Enemy (9).jpg");
    }

    public Fae(int health, String imageFilename){
        super("Fae", CreatureType.FAIRY, 1, health,imageFilename);
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // -1 firewood
        game.adjFirewood(-1);
    }
}
