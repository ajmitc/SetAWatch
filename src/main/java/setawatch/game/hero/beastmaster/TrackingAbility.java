package setawatch.game.hero.beastmaster;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class TrackingAbility extends HeroAbility {

    public TrackingAbility(){
        super("Tracking", "Beastmaster (5).jpg", "Beastmaster Back (5).jpg");
        passive = true;
    }

    @Override
    public void activate(Game game) {
        // TODO Every time a first position power triggers, you may reveal a card in the line
    }
}
