package setawatch.game.creature;

import setawatch.game.Game;

public class Bandit2 extends Creature{

    public Bandit2(){
        super("Bandit", CreatureType.HUMAN, 1, 7, "Enemy (22).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // TODO bandit steals one available dice of the groups choosing
    }
}
