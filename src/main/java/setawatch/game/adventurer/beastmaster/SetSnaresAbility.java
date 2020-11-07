package setawatch.game.adventurer.beastmaster;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class SetSnaresAbility extends AdventurerAbility {

    public SetSnaresAbility(){
        super("Set Snares", "Beastmaster (2).jpg", "Beastmaster Back (2).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Tame a revealed forest creature or put a revealed creature back on top of the creature deck
    }
}
