package setawatch.game.adventurer.cleric;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class Banish extends AdventurerAbility {

    public Banish(){
        super("Banish", "Cleric (3).jpg", "Cleric Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Place an Unhallowed or Undead at the bottom of the creature deck
    }
}
