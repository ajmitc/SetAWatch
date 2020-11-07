package setawatch.game.adventurer.elfranger;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class SharpshooterAbility extends AdventurerAbility {

    public SharpshooterAbility(){
        super("Sharpshooter", "Elf Ranger (5).jpg", "Elf Ranger Back (5).jpg");
        passive = true;
    }

    @Override
    public void activate(Game game) {
        // TODO Once per round, you may reroll and reuse a die spent on a direct attack
    }
}
