package setawatch.game.creature;

import setawatch.game.Game;

public class ZombieHorde2 extends Creature{

    public ZombieHorde2(){
        super("Zombie Horde", CreatureType.UNDEAD, 1, 8, "Enemy (30).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO Zombie Horde's health is combined with the creature's base health on top of the graveyard
    }
}
