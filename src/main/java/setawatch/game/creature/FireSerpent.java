package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class FireSerpent extends Creature{
    private Game game;

    public FireSerpent(){
        super("Fire Serpent", CreatureType.FIRE_ELEMENTAL, 1, 0, "Enemy (31).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        this.game = game;
        // Fire Serpent's base health is equal to the current firewood value
    }

    @Override
    public int getHealth(){
        if (this.game != null)
            return this.game.getFirewood();
        return super.getHealth();
    }
}
