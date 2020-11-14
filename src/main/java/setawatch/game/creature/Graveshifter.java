package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.logging.Logger;

public class Graveshifter extends Creature{
    private Logger logger = Logger.getLogger(Graveshifter.class.getName());

    public Graveshifter(){
        super("Graveshifter", CreatureType.UNDEAD, 1, 2, "Enemy (35).jpg"); // try 36 too
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Swap this card with the creature on top of the graveyard
        if (!game.getGraveyard().isEmpty() && game.getGraveyard().peek().getType() != CreatureType.SUMMON) {
            int index = game.getLine().indexOf(this);
            game.getLine().remove(this);
            game.getLine().add(index, game.getGraveyard().draw());
        }
        else
            logger.info("Graveyard is empty or top card is Summon, keeping Graveshifter in place");
    }
}
