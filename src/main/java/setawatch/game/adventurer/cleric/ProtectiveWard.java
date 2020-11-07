package setawatch.game.adventurer.cleric;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class ProtectiveWard extends AdventurerAbility {

    public ProtectiveWard(){
        super("Protective Ward", "Cleric (5).jpg", "Cleric Back (5).jpg");
        passive = true;
    }

    @Override
    public void activate(Game game) {
        // TODO Once per round, let a creature in first position into the Horde without taking damage
    }
}
