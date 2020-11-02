package setawatch.game.hero.beastmaster;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class TameBeastAbility extends HeroAbility {

    public TameBeastAbility(){
        super("Tame Beast", "Beastmaster (1).jpg", "Beastmaster Back (1).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Tame a revealed forest creature
    }
}
