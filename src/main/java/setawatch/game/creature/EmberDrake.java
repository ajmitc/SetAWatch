package setawatch.game.creature;

import setawatch.game.Game;

public class EmberDrake extends Creature{

    public EmberDrake(){
        super("Ember Drake", CreatureType.DRAGON, 2, 11, "Enemy (11).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // TODO Exhaust 2 Adventurer Ability cards and -3 firewood
        game.adjFirewood(-3);
    }
}
