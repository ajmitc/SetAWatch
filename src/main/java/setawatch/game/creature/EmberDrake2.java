package setawatch.game.creature;

import setawatch.game.Game;

public class EmberDrake2 extends Creature{

    public EmberDrake2(){
        super("Ember Drake", CreatureType.DRAGON, 2, 12, "Enemy (12).jpg");
    }

    @Override
    public void onFirstPosition(Game game) {
        super.onFirstPosition(game);
        // TODO Exhaust 2 Adventurer Ability cards and -3 firewood
        game.adjFirewood(-3);
    }
}
