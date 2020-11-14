package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class ForestGiant extends Creature{
    private Game game;
    private View view;

    public ForestGiant(){
        this(7, "Enemy (1).jpg");
    }

    public ForestGiant(int health, String imageFilename){
        super("Forest Giant", CreatureType.GIANT, 2, health, imageFilename);
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        this.game = game;
        this.view = view;
        // Giant's health is always combined with the creature's health behind it.  Reveal next creature in line if necessary.
        getHealth();
    }

    public int getHealth(){
        int nextCreatureInLineHealth = 0;
        if (this.game != null) {
            int index = this.game.getLine().indexOf(this);
            if (this.game.getLine().size() > index + 1) {
                Creature nextCreatureInLine = this.game.getLine().get(index + 1);
                if (!nextCreatureInLine.isRevealed()){
                    nextCreatureInLine.setRevealed(true);
                    this.view.getGamePanel().refresh();
                    nextCreatureInLine.onReveal(game, view);
                }
                nextCreatureInLineHealth = nextCreatureInLine.getHealth();
            }
        }
        return super.getHealth() + nextCreatureInLineHealth;
    }
}
