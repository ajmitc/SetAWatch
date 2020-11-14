package setawatch.game.adventurer.wizard;

import setawatch.game.Game;
import setawatch.game.adventurer.AdventurerAbility;

public class IlluminateAbility extends AdventurerAbility {

    public IlluminateAbility(){
        super("Illuminate", "Wizard (5).jpg", "Wizard Back (5).jpg");
        passive = true;
    }

    @Override
    public void activate(Game game) {
        // TODO For every firewood you spend, you may reveal the next creature in line
    }
}
