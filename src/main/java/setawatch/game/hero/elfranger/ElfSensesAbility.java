package setawatch.game.hero.elfranger;

import setawatch.game.Game;
import setawatch.game.hero.HeroAbility;

public class ElfSensesAbility extends HeroAbility {

    public ElfSensesAbility(){
        super("Elf Senses", "Elf Ranger (1).jpg", "Elf Ranger Back (1).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Reveal one more creature than the campfire currently allows until the end of the round.  Prevent the next Reveal power from triggering.
    }
}
