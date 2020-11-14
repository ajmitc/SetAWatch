package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.List;
import java.util.stream.Collectors;

public class GoblinChief extends Creature{

    public GoblinChief(){
        super("Goblin Chief", CreatureType.UNHALLOWED, 2, 8, "Unhallowed (7).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Add all goblins from the creature deck and graveyard to the front of the line
        List<Creature> goblins = game.getCreatureDeck().getDeck().stream().filter(c -> c.getType() == CreatureType.GOBLIN).collect(Collectors.toList());
        for (Creature goblin: goblins){
            game.getCreatureDeck().getDeck().remove(goblin);
            game.getLine().add(0, goblin);
            goblin.setRevealed(true);
            // Goblins move to first position when revealed, which we already did, so don't need to call onReveal
        }
        goblins = game.getGraveyard().getDeck().stream().filter(c -> c.getType() == CreatureType.GOBLIN).collect(Collectors.toList());
        for (Creature goblin: goblins){
            game.getGraveyard().getDeck().remove(goblin);
            game.getLine().add(0, goblin);
            goblin.setRevealed(true);
            // Goblins move to first position when revealed, which we already did, so don't need to call onReveal
        }
    }
}
