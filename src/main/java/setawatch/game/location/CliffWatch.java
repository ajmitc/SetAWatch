package setawatch.game.location;

import setawatch.game.Game;

public class CliffWatch extends Location{

    public CliffWatch(){
        super("Cliff Watch", false, -4, 7, "Location (5).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setAdventurerRangeBonus(game.getAdventurerRangeBonus() + 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setAdventurerRangeBonus(game.getAdventurerRangeBonus() - 1);
    }
}
