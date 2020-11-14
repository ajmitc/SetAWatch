package setawatch.game.creature;

import java.util.*;

public class CreatureDeck {
    private static final Random GEN = new Random(new Date().getTime());

    private List<Creature> deck = new ArrayList<>();

    public CreatureDeck(){
    }

    public Creature draw(){
        if (deck.isEmpty())
            return null;
        return deck.remove(0);
    }

    public Creature pop(){
        if (deck.isEmpty())
            return null;
        return deck.remove(deck.size() - 1);
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public void addToTop(Creature creature){
        deck.add(0, creature);
    }

    public void addToBottom(Creature creature){
        deck.add(creature);
    }

    public void add(int index, Creature creature){
        deck.add(index, creature);
    }

    public void add(Creature creature){
        deck.add(creature);
    }

    public Creature peek(){
        return deck.get(0);
    }

    public int size(){ return deck.size(); }

    public boolean isEmpty(){ return size() == 0; }

    public List<Creature> getDeck() {
        return deck;
    }
}
