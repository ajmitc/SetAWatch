package setawatch.game.adventurer.elfranger;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class FarSightAbility extends AdventurerAbility {

    public FarSightAbility(){
        super("Far Sight", "Elf Ranger (3).jpg", "Elf Ranger Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Increase the range of all adventurers by one until the end of the round
    }
}
