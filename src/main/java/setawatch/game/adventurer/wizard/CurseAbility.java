package setawatch.game.adventurer.wizard;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class CurseAbility extends AdventurerAbility {

    public CurseAbility(){
        super("Curse", "Wizard (1).jpg", "Wizard Back (1).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Defeat a creature that already has an attack die assigned to it
    }
}
