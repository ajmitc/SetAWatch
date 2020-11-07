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
        deck.add(new Bear());
        deck.add(new Bear2());
        deck.add(new Eagle());
        deck.add(new Eagle2());
        deck.add(new Wolf());
        deck.add(new Wolf2());
        deck.add(new Fae());
        deck.add(new Fae2());
        deck.add(new EmberDrake());
        deck.add(new EmberDrake2());
        deck.add(new Acolyte());
        deck.add(new Acolyte2());
        deck.add(new FellBeast());
        deck.add(new FellBeast2());
        deck.add(new Wyvern());
        deck.add(new Wyvern2());
        deck.add(new Goblins());
        deck.add(new Goblins2());
        deck.add(new Bandit());
        deck.add(new Bandit2());
        deck.add(new MetalGolem());
        deck.add(new MetalGolem2());
        deck.add(new Skeletons());
        deck.add(new Skeletons2());
        deck.add(new Vampire());
        deck.add(new Vampire2());
        deck.add(new ZombieHorde());
        deck.add(new ZombieHorde2());
        deck.add(new FireSerpent());
        deck.add(new FireSerpent());

        unhallowed.add(new Lich());

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
