package setawatch.game.hero.beastmaster;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class SetSnaresAbility extends HeroAbility {

    public SetSnaresAbility(){
        super("Set Snares", "Beastmaster (2).jpg", "Beastmaster Back (2).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Tame a revealed forest creature or put a revealed creature back on top of the creature deck
    }
}
