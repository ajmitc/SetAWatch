package setawatch.game.adventurer.beastmaster;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class TrackingAbility extends AdventurerAbility {

    public TrackingAbility(){
        super("Tracking", "Beastmaster (5).jpg", "Beastmaster Back (5).jpg");
        passive = true;
    }

    @Override
    public void activate(Game game) {
        // TODO Every time a first position power triggers, you may reveal a card in the line
    }
}
