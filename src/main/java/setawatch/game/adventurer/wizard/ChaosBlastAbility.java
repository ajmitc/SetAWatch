package setawatch.game.adventurer.wizard;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class ChaosBlastAbility extends AdventurerAbility {

    public ChaosBlastAbility(){
        super("Chaos Blast", "Wizard (4).jpg", "Wizard Back (4).jpg");
    }

    @Override
    public void activate(Game game) {
        // TODO Defeat two revealed adjacent creatures and add a new creature to the end of the line
    }
}
