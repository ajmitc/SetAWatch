package setawatch.game.hero.elfranger;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class LongShotAbility extends HeroAbility {

    public LongShotAbility(){
        super("Long Shot", "Elf Ranger (2).jpg", "Elf Ranger Back (2).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Defeat any unrevealed creature card
    }
}
