package setawatch.game.creature;

import setawatch.game.Dice;
import setawatch.game.Game;
import setawatch.view.View;

import java.util.Comparator;
import java.util.Optional;

public class Vampire extends Creature{

    public Vampire(){
        super("Vampire", CreatureType.UNDEAD, 1, 1, "Enemy (27).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Steal the highest available die to become this card's health value
        Optional<Dice> opt =
                game.getAdventurers().stream().flatMap(a -> a.getDice().stream().filter(d -> !d.isAssigned())).sorted(new Comparator<Dice>() {
                    @Override
                    public int compare(Dice o1, Dice o2) {
                        return o1.getValue() > o2.getValue()? -1: o1.getValue() < o2.getValue()? 1: 0;
                    }
                }).findFirst();
        if (opt.isPresent()){
            opt.get().setAssigned(true);
            setStolenDice(opt.get());
        }
    }

    @Override
    public int getHealth() {
        if (getStolenDice() != null)
            return getStolenDice().getValue();
        return super.getHealth();
    }

    @Override
    public void onDefeat(Game game, View view) {
        super.onDefeat(game, view);
        if (getStolenDice() != null) {
            setStolenDice(null);
        }
    }
}
