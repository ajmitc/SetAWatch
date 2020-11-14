package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.Optional;

public class Acolyte extends Creature{

    public Acolyte(){
        this(6, "Enemy (13).jpg");
    }

    public Acolyte(int health, String imageFilename){
        super("Acolyte", CreatureType.HUMAN, 1, health, imageFilename);
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Draw a creature and add it to the horde
        Creature creature = game.getCreatureDeck().draw();
        game.getHorde().addToTop(creature);
    }

    @Override
    public void onFirstPosition(Game game, View view) {
        super.onFirstPosition(game, view);
        // Reshuffle a Summon card back into the creature deck, if there is one in the graveyard
        Optional<Creature> summon = game.getGraveyard().getDeck().stream().filter(c -> c.getType() == CreatureType.SUMMON).findFirst();
        if (summon.isPresent()){
            game.getGraveyard().getDeck().remove(summon.get());
            game.getCreatureDeck().add(summon.get());
            game.getCreatureDeck().shuffle();
        }
    }
}
