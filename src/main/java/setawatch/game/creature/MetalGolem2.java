package setawatch.game.creature;

import setawatch.game.Game;

public class MetalGolem2 extends Creature{

    public MetalGolem2(){
        super("Metal Golem", CreatureType.GOLEM, 2, 8, "Enemy (24).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO No direct attacks can be assigned to creatures in the line behind the Metal Golem
    }
}
