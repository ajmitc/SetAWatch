package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class PhantomCat extends Creature{

    public PhantomCat(){
        super("Phantom Cat", CreatureType.UNHALLOWED, 2, 10, "Unhallowed (10).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        // Immune to damage and abilities while there are cards adjacent to cat on both sides
        int index = game.getLine().indexOf(this);
        if (index > 0 && index < game.getLine().size() - 1){
            setDirectAttackAllowed(false);
            setIndirectAttackAllowed(false);
        }
        else {
            setDirectAttackAllowed(true);
            setIndirectAttackAllowed(true);
        }

    }
}
