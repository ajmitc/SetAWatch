package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.view.View;

public class ZombieHorde extends Creature{
    private Game game;

    public ZombieHorde(){
        this(7, "Enemy (29).jpg");
    }

    public ZombieHorde(int health, String imageFilename){
        super("Zombie Horde", CreatureType.UNDEAD, 1, health, imageFilename);
    }

    @Override
    public void onGoing(Game game, View view) {
        super.onGoing(game, view);
        this.game = game;
        // Zombie Horde's health is combined with the creature's base health on top of the graveyard
    }

    @Override
    public int getHealth() {
        int topCreatureHealth = 0;
        if (this.game != null && !this.game.getGraveyard().isEmpty()){
            topCreatureHealth = this.game.getGraveyard().peek().getBaseHealth();
        }
        return super.getBaseHealth() + topCreatureHealth;
    }
}
