package setawatch.game.adventurer.elfranger;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class SlumberAbility extends AdventurerAbility {

    public SlumberAbility(){
        super("Slumber", "Elf Ranger (4).jpg", "Elf Ranger Back (4).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Conceal a revealed creature, recover any die stolen by that creature and push it to the back of the line
    }
}
