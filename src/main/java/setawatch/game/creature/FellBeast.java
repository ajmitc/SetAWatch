package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class FellBeast extends Creature{
    private boolean activated = false;

    public FellBeast(){
        this(10, "Enemy (15).jpg");
    }

    public FellBeast(int health, String imageFilename){
        super("Fell Beast", CreatureType.FIRE_DEMON, 2, health, imageFilename);
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        if (!activated) {
            // Draw another creature and place it in front of Fell Beast (activate only once)
            activated = true;
            Creature creature = game.getCreatureDeck().draw();
            game.getLine().add(0, creature);
            creature.setRevealed(true);
            view.getGamePanel().refresh();
            creature.onReveal(game, view);
            creature.onFirstPosition(game, view);
        }
    }
}
