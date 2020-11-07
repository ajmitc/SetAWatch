package setawatch.game.creature;

import setawatch.game.Game;

public class MetalGolem extends Creature{

    public MetalGolem(){
        super("Metal Golem", CreatureType.GOLEM, 2, 8, "Enemy (23).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO No direct attacks can be assigned to creatures in the line behind the Metal Golem
    }
}
