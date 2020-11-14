package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Pyromancer extends Creature{

    public Pyromancer(){
        super("Pyromancer", CreatureType.UNHALLOWED, 1, 9, "Unhallowed (3).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Place this card at the end of the line
        game.getLine().remove(this);
        game.getLine().add(this);
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        // Every creature that goes to the graveyard before Pyromancer reduces the firewood by 1
        game.setFirewoodReductionWhenSendingToGraveyard(1);
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        game.setFirewoodReductionWhenSendingToGraveyard(0);
    }
}
