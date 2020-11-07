package setawatch.game.adventurer.elfranger;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class LongShotAbility extends AdventurerAbility {

    public LongShotAbility(){
        super("Long Shot", "Elf Ranger (2).jpg", "Elf Ranger Back (2).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Defeat any unrevealed creature card
    }
}
