package setawatch.game.adventurer.dwarf;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class ThrowAxeAbility extends AdventurerAbility {

    public ThrowAxeAbility(){
        super("Throw Axe", "Dwarf (3).jpg", "Dwarf Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Deal 8 damage to a revealed creature in second position
    }
}
