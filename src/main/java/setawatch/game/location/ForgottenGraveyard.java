package setawatch.game.location;

import setawatch.game.Game;

public class ForgottenGraveyard extends Location{

    public ForgottenGraveyard(){
        super("Forgotten Graveyard", false, -2, 6, "Location (3).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setSendDefeatedUndeadToBottomOfCreatureDeck(true);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setSendDefeatedUndeadToBottomOfCreatureDeck(false);
    }
}
