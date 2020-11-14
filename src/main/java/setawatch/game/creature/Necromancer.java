package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Necromancer extends Creature{

    public Necromancer(){
        super("Necromancer", CreatureType.UNHALLOWED, 2, 9, "Unhallowed (8).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // TODO Place the top two undead creatures in the graveyard in front of the necromancer in line; weakest first
        List<Creature> undead = game.getGraveyard().getDeck().stream().filter(c -> c.getType() == CreatureType.UNDEAD).collect(Collectors.toList());
        while (undead.size() > 2){
            undead.remove(undead.size() - 1);
        }
        undead.sort(new Comparator<Creature>() {
            @Override
            public int compare(Creature o1, Creature o2) {
                return o1.getHealth() < o2.getHealth()? -1:
                        o1.getHealth() > o2.getHealth()? 1: 0;
            }
        });
        if (undead.size() == 2){
            game.getLine().add(0, undead.get(1));
        }
        if (undead.size() >= 1){
            game.getLine().add(0, undead.get(0));
            view.getGamePanel().refresh();
            undead.get(0).onFirstPosition(game, view);
        }
    }
}
