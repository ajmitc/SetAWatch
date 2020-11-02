package setawatch.game.location;

import setawatch.game.Game;

public class AncientCrypt extends Location{

    public AncientCrypt(){
        super("Ancient Crypt", false, -3, 7, "Location (2).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setChooseUnhallowed(true);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setChooseUnhallowed(false);
    }
}
