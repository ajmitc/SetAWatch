package setawatch.game.adventurer.cleric;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class BlindingLight extends AdventurerAbility {

    public BlindingLight(){
        super("Blinding Light", "Cleric (4).jpg", "Cleric Back (4).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Reveal up to 2 creatures, negating any reveal abilities
    }
}
