package setawatch.game.adventurer.wizard;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class PolymorphAbility extends AdventurerAbility {

    public PolymorphAbility(){
        super("Polymorph", "Wizard (3).jpg", "Wizard Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Swap out a creature for any other in the graveyard
    }
}
