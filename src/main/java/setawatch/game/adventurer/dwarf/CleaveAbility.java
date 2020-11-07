package setawatch.game.adventurer.dwarf;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class CleaveAbility extends AdventurerAbility {

    public CleaveAbility(){
        super("Cleave", "Dwarf (5).jpg", "Dwarf Back (5).jpg");
        passive = true;
    }

    @Override
    public void activate(Game game) {
        // TODO If you defeat a creature with a die and there is a remainder you may keep that die at the remaining value
    }
}
