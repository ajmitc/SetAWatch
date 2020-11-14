package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.List;
import java.util.stream.Collectors;

public class CorruptDruid extends Creature{

    public CorruptDruid(){
        super("Corrupt Druid", CreatureType.UNHALLOWED, 2, 11, "Unhallowed (11).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Place this card and all revealed forest creatures (including tamed) into the Horde unless it's the final round
        if (game.getLocations().isEmpty())
            return;
        game.getHorde().addToTop(this);
        // Get revealed in line
        List<Creature> revealedInLine = game.getLine().stream().filter(c -> c.getType() == CreatureType.FOREST_CREATURE).collect(Collectors.toList());
        for (Creature creature: revealedInLine){
            game.getLine().remove(creature);
            game.getHorde().addToTop(creature);
        }
        // Get Tamed
        game.getAdventurers().stream().forEach(a -> {
            for (Creature creature: a.getTamedCreatures()){
                game.getHorde().addToTop(creature);
            }
            a.getTamedCreatures().clear();
        });
    }
}
