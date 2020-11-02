package setawatch.game.location;

import setawatch.game.Game;

public class DarkWoods extends Location{

    public DarkWoods(){
        super("Dark Woods", false, 2, 7, "Location (9).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setForestCreatureHealthBonus(game.getForestCreatureHealthBonus() + 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setForestCreatureHealthBonus(game.getForestCreatureHealthBonus() - 1);
    }
}
