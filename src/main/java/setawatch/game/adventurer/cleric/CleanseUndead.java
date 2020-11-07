package setawatch.game.adventurer.cleric;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class CleanseUndead extends AdventurerAbility {

    public CleanseUndead(){
        super("Cleanse Undead", "Cleric (2).jpg", "Cleric Back (2).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO All revealed Undead creatures go to the graveyard.  You can decide the order they are defeated.
    }
}
