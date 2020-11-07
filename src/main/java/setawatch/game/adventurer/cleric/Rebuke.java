package setawatch.game.adventurer.cleric;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class Rebuke extends AdventurerAbility {

    public Rebuke(){
        super("Rebuke", "Cleric (1).jpg", "Cleric Back (1).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Move a revealed creature back to a different position in the line or defeat an Undead in first position
    }
}
