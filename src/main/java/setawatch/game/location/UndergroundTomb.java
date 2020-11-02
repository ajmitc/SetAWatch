package setawatch.game.location;

import setawatch.game.Game;

public class UndergroundTomb extends Location{

    public UndergroundTomb(){
        super("Underground Tomb", false, -3, 6, "Location (8).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        game.setCreaturesRevealedModification(game.getCreaturesRevealedModification() - 1);
    }

    @Override
    public void leaveCamp(Game game) {
        game.setCreaturesRevealedModification(game.getCreaturesRevealedModification() + 1);
    }
}
