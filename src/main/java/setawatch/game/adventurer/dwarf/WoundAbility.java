package setawatch.game.adventurer.dwarf;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class WoundAbility extends AdventurerAbility {

    public WoundAbility(){
        super("Wound", "Dwarf (1).jpg", "Dwarf Back (1).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Lower a revealed creature's health by half (round down)
    }
}
