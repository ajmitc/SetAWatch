package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class MetalGolem extends Creature{

    public MetalGolem(){
        super("Metal Golem", CreatureType.GOLEM, 2, 8, "Enemy (23).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        // No direct attacks can be assigned to creatures in the line behind the Metal Golem
        int index = game.getLine().indexOf(this);
        for (int i = index + 1; i < game.getLine().size(); ++i){
            game.getLine().get(i).setDirectAttackAllowed(false);
        }
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        for (Creature creature: game.getLine()){
            creature.setDirectAttackAllowed(true);
        }
    }
}
