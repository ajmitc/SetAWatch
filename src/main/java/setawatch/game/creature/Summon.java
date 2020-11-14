package setawatch.game.creature;

import setawatch.game.Game;
import setawatch.game.PhaseStep;
import setawatch.view.View;

public class Summon extends Creature{
    public Summon(){
        super("Summon", CreatureType.SUMMON, 0, 0, "Summon (1).jpg");
    }

    @Override
    public void onReveal(Game game, View view) {
        super.onReveal(game, view);
        // Immediately exhaust an Adventurer card, then replace this card with the top card from the Unhallowed deck.
        game.setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES_EXHAUST_ADVENTURER_ABILITY);
        game.setNumAdventurerAbilityCardsToExhaust(1);

        int index = game.getLine().indexOf(this);
        Creature unhallowed = game.getUnhallowedDeck().draw();
        game.getLine().add(index, unhallowed);
        game.getLine().remove(this);
        // Put this card in the graveyard.
        game.getGraveyard().addToTop(this);
    }
}
