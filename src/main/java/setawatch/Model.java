package setawatch;

import setawatch.game.Game;

public class Model {
    private Game game;

    public Model(){

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
