package setawatch.game.location;

import setawatch.game.Game;

public class BarrenPlains extends Location{

    public BarrenPlains(){
        super("Barren Plains", false, 0, 6, "Location (6).jpg");
    }

    @Override
    public void makeCamp(Game game) {
        // Reduce firewood by half (rounded down)
        int firewood = game.getFirewood();
        int lost = firewood / 2;
        game.setFirewood(firewood - lost);
    }

    @Override
    public void leaveCamp(Game game) {
    }
}
