package setawatch.game.creature;

import java.util.*;

public class CreatureDeck {
    private static final Random GEN = new Random(new Date().getTime());

    private List<Creature> deck = new ArrayList<>();
    private List<Creature> graveyard = new ArrayList<>();
    private List<Creature> unhallowed = new ArrayList<>();

    public CreatureDeck(){
        deck.add(new ForestGiant());
        deck.add(new ForestGiant2());

        Collections.shuffle(deck);
        Collections.shuffle(unhallowed);
    }

    public Creature draw(){
        if (deck.isEmpty())
            return null;
        return deck.remove(0);
    }

    /**
     * Randomly select 28 cards + 2 Acolytes
     */
    public void buildDeck(int numSummon){
        Collections.shuffle(deck);
        while (deck.size() > 28)
            deck.remove(deck.size() - 1);
        deck.add(new Acolyte());
        deck.add(new Acolyte());
        Collections.shuffle(deck);

        int cardsInEachPile = deck.size() / numSummon;
        for (int i = numSummon - 1; i >= 0; --i){
            int index = (i * cardsInEachPile) + GEN.nextInt(cardsInEachPile);
            deck.add(index, new Summon());
        }

        while (unhallowed.size() > 8)
            unhallowed.remove(unhallowed.size() - 1);
    }

    public void addToTop(Creature creature){
        deck.add(0, creature);
    }

    public void addToBottom(Creature creature){
        deck.add(creature);
    }

    public void addToGraveyard(Creature creature){
        graveyard.add(0, creature);
    }

    public List<Creature> getGraveyard() {
        return graveyard;
    }

    public Creature peekGraveyard(){
        return graveyard.get(0);
    }

    public List<Creature> getUnhallowed() {
        return unhallowed;
    }
}
