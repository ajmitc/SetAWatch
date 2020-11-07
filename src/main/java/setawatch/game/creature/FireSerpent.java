package setawatch.game.creature;

import setawatch.game.Game;

public class FireSerpent extends Creature{

    public FireSerpent(){
        super("Fire Serpent", CreatureType.FIRE_ELEMENTAL, 1, 0, "Enemy (31).jpg");
    }

    @Override
    public void onGoing(Game game) {
        super.onGoing(game);
        // TODO Fire Serpent's base health is equal to the current firewood value
    }
}
