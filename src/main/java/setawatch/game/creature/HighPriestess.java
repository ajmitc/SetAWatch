package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HighPriestess extends Creature{

    public HighPriestess(){
        super("High Priestess", CreatureType.UNHALLOWED, 2, 9, "Unhallowed (13).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Place the top two Acolytes from the graveyard in front of the High Priestess in line; weakest first
        List<Creature> acolytes = game.getGraveyard().getDeck().stream().filter(c -> c.getType() == CreatureType.ACOLYTE).collect(Collectors.toList());
        while (acolytes.size() > 2){
            acolytes.remove(acolytes.size() - 1);
        }
        acolytes.sort(new Comparator<Creature>() {
            @Override
            public int compare(Creature o1, Creature o2) {
                return o1.getHealth() < o2.getHealth()? -1:
                        o1.getHealth() > o2.getHealth()? 1: 0;
            }
        });
        if (acolytes.size() == 2){
            game.getLine().add(0, acolytes.get(1));
        }
        if (acolytes.size() >= 1){
            game.getLine().add(0, acolytes.get(0));
            view.getGamePanel().refresh();
            acolytes.get(0).onFirstPosition(game, view);
        }
    }
}
