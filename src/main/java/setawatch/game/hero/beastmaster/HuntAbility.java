package setawatch.game.hero.beastmaster;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class HuntAbility extends HeroAbility {

    public HuntAbility(){
        super("Hunt", "Beastmaster (4).jpg", "Beastmaster Back (4).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Tame a forest creature in the graveyard
    }
}
