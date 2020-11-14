package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Hydra extends Creature{

    public Hydra(){
        super("Hydra", CreatureType.UNHALLOWED, 3, 9, "Unhallowed (9).jpg");
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        // if there are still creatures in the line, add the Hydra to the back of the line
        if (!game.getLine().isEmpty()){
            game.getLine().add(this);
        }
    }
}
