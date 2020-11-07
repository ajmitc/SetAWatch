package setawatch.game.adventurer.beastmaster;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class HuntAbility extends AdventurerAbility {

    public HuntAbility(){
        super("Hunt", "Beastmaster (4).jpg", "Beastmaster Back (4).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Tame a forest creature in the graveyard
    }
}
