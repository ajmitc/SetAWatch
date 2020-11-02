package setawatch.game.location;

import setawatch.game.Game;

public class ForbiddenTower extends Location{

    public ForbiddenTower(){
        super("Forbidden Tower", false, -2, 9, "Location (1).jpg");
        finalLocation = true;
    }

    @Override
    public void makeCamp(Game game) {
        game.setFirewoodGainedFromExhaustingAdventurerCard(game.getFirewoodGainedFromExhaustingAdventurerCard() + 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setFirewoodGainedFromExhaustingAdventurerCard(game.getFirewoodGainedFromExhaustingAdventurerCard() - 1);
    }
}
