package setawatch.game.adventurer.dwarf;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class GiantsBaneAbility extends AdventurerAbility {

    public GiantsBaneAbility(){
        super("Giant's Bane", "Dwarf (2).jpg", "Dwarf Back (2).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO All dice assigned to creatures with 10+ health are doubled until the end of the round
    }
}
