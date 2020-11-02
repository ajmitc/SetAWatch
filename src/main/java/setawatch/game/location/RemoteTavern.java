package setawatch.game.location;

import setawatch.game.Game;

public class RemoteTavern extends Location{

    public RemoteTavern(){
        super("Remote Tavern", false, 0, 5, "Location (10).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setNumCardsRefreshedInCamp(game.getNumCardsRefreshedInCamp() + 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setNumCardsRefreshedInCamp(game.getNumCardsRefreshedInCamp() - 1);
    }
}
