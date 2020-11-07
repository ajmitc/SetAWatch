package setawatch.game.creature;

import setawatch.game.Game;

public class Lich extends Creature{

    public Lich(){
        super("Lich", CreatureType.UNHALLOWED, 3, 14, "Unhallowed (1).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO No ability cards can affect any creatures behind the Lich in line
    }
}
