package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WyvernRider extends Creature{

    public WyvernRider(){
        super("Wyvern Rider", CreatureType.UNHALLOWED, 2, 12, "Unhallowed (5).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // All Wyverns in the graveyard are placed behind the rider in line
        int index = game.getLine().indexOf(this);
        List<Creature> wyverns = game.getGraveyard().getDeck().stream().filter(c -> c.getType() == CreatureType.DRAGON).collect(Collectors.toList());
        Collections.shuffle(wyverns);
        for (Creature wyvern: wyverns){
            game.getGraveyard().getDeck().remove(wyvern);
            game.getLine().add(index + 1, wyvern);
        }
    }
}
