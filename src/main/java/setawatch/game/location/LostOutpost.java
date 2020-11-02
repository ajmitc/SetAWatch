package setawatch.game.location;

import setawatch.game.Game;

public class LostOutpost extends Location{

    public LostOutpost(){
        super("Lost Outpost", false, 0, 8, "Location (4).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setAdventurerIncreaseLowestDieRollBy(game.getAdventurerIncreaseLowestDieRollBy() + 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setAdventurerIncreaseLowestDieRollBy(game.getAdventurerIncreaseLowestDieRollBy() - 1);
    }
}
