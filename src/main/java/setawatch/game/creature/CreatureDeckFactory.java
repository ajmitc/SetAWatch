package setawatch.game.creature;

public class CreatureDeckFactory {
    public static CreatureDeck getCreatureDeck() {
        CreatureDeck deck = new CreatureDeck();
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
        deck.add(new FellBeast());
        deck.add(new FellBeast2());
        deck.add(new Wyvern());
        deck.add(new Wyvern2());
        deck.add(new Goblins());
        deck.add(new Goblins2());
        deck.add(new Bandit());
        deck.add(new Bandit());
        deck.add(new MetalGolem());
        deck.add(new MetalGolem());
        deck.add(new Skeletons());
        deck.add(new Skeletons2());
        deck.add(new Vampire());
        deck.add(new Vampire());
        deck.add(new ZombieHorde());
        deck.add(new ZombieHorde2());
        deck.add(new FireSerpent());
        deck.add(new FireSerpent());
        deck.add(new Shade());
        deck.add(new Shade());
        deck.add(new Graveshifter());
        deck.add(new Graveshifter());
        deck.add(new Treant());
        deck.add(new Treant());
        return deck;
    }


    public static CreatureDeck getUnhallowedDeck() {
        CreatureDeck unhallowed = new CreatureDeck();
        unhallowed.add(new Lich());
        unhallowed.add(new WyvernRider());
        unhallowed.add(new WraithLord());
        unhallowed.add(new Pyromancer());
        unhallowed.add(new Warlord());
        unhallowed.add(new PhantomCat());
        unhallowed.add(new Hydra());
        unhallowed.add(new Necromancer());
        unhallowed.add(new GoblinChief());
        unhallowed.add(new Werewolf());
        unhallowed.add(new HighPriestess());
        unhallowed.add(new Minotaur());
        unhallowed.add(new CorruptDruid());
        return unhallowed;
    }

    private CreatureDeckFactory(){}
}
