package setawatch.game.hero.elfranger;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class FarSightAbility extends HeroAbility {

    public FarSightAbility(){
        super("Far Sight", "Elf Ranger (3).jpg", "Elf Ranger Back (3).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Increase the range of all adventurers by one until the end of the round
    }
}
