package setawatch.game.location;

import setawatch.game.Game;

public class FetidSwamp extends Location{

    public FetidSwamp(){
        super("Fetid Swamp", false, -3, 6, "Location (7).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setFirewoodReductionWhenFirstPositionPowerActivated(game.getFirewoodReductionWhenFirstPositionPowerActivated() + 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setFirewoodReductionWhenFirstPositionPowerActivated(game.getFirewoodReductionWhenFirstPositionPowerActivated() - 1);
    }
}
