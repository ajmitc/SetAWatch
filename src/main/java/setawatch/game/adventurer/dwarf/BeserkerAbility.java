package setawatch.game.adventurer.dwarf;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class BeserkerAbility extends AdventurerAbility {

    public BeserkerAbility(){
        super("Beserker", "Dwarf (4).jpg", "Dwarf Back (4).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Defeat a revealed creature of lesser health adjacent to a creature that was just defeated
    }
}
