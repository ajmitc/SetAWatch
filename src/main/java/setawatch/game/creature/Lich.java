package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Lich extends Creature{

    public Lich(){
        super("Lich", CreatureType.UNHALLOWED, 3, 14, "Unhallowed (1).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        // No ability cards can affect any creatures behind the Lich in line
        int index = game.getLine().indexOf(this);
        for (int i = index + 1; i < game.getLine().size(); ++i){
            game.getLine().get(i).setIndirectAttackAllowed(false);
        }
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        for (Creature creature: game.getLine()){
            creature.setIndirectAttackAllowed(true);
        }
    }
}
