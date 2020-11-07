package setawatch.game.creature;

import setawatch.game.Game;

public class ZombieHorde extends Creature{

    public ZombieHorde(){
        super("Zombie Horde", CreatureType.UNDEAD, 1, 7, "Enemy (29).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO Zombie Horde's health is combined with the creature's base health on top of the graveyard
    }
}
