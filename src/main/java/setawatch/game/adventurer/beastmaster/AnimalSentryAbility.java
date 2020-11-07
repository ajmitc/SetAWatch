package setawatch.game.adventurer.beastmaster;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class AnimalSentryAbility extends AdventurerAbility {

    public AnimalSentryAbility(){
        super("Animal Sentry", "Beastmaster (3).jpg", "Beastmaster Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Spend a tamed forest creature to defeat the last creature in line
    }
}
