package setawatch.game.hero.beastmaster;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class AnimalSentryAbility extends HeroAbility {

    public AnimalSentryAbility(){
        super("Animal Sentry", "Beastmaster (3).jpg", "Beastmaster Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Spend a tamed forest creature to defeat the last creature in line
    }
}
