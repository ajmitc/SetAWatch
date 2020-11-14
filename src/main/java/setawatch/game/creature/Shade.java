package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class Shade extends Creature{
    private Game game;

    public Shade(){
        // Could also try Enemy (34).jpg if image is not good
        super("Shade", CreatureType.SHADOW_ELEMENTAL, 1, 0, "Enemy (33).jpg");
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        this.game = game;
        // Shade's base health is equal to 16 minus the current firewood value
    }

    @Override
    public int getBaseHealth() {
        if (this.game != null) {
            return 16 - this.game.getFirewood();
        }
        return super.getBaseHealth();
    }
}
