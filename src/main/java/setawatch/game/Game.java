package setawatch.game;

import setawatch.game.creature.*;
import setawatch.game.adventurer.Adventurer;
import setawatch.game.location.Location;
import setawatch.game.location.LocationDeck;
import setawatch.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Phase phase = Phase.SETUP;
    private PhaseStep phaseStep = PhaseStep.START_PHASE;

    private List<Adventurer> adventurers = new ArrayList<>();
    private List<Creature> line          = new ArrayList<>();
    private CreatureDeck creatureDeck    = CreatureDeckFactory.getCreatureDeck();
    private CreatureDeck unhallowedDeck  = CreatureDeckFactory.getUnhallowedDeck();
    private CreatureDeck graveyard       = new CreatureDeck();
    private CreatureDeck horde           = new CreatureDeck();
    private List<Location> locations     = new ArrayList<>();
    private LocationDeck locationDeck    = new LocationDeck();
    private Location currentLocation;

    private int firewood = 7;

    // Rules
    private int firewoodGainedFromExhaustingAdventurerCard = 1;
    private int adventurerRangeBonus = 0;
    private int adventurerIncreaseLowestDieRollBy = 0;
    private boolean sendDefeatedUndeadToBottomOfCreatureDeck = false; // instead of graveyard
    private boolean chooseUnhallowed = false;
    private int numCardsRefreshedInCamp = 1;
    private int forestCreatureHealthBonus = 0;
    private int creaturesRevealedModification = 0;
    private int firewoodReductionWhenFirstPositionPowerActivated = 0;
    private int firewoodReductionWhenSendingToGraveyard = 0;

    // Game play

    // This is the creature that is currently activating (perfoming a callback (reveal, first position, etc) or attacking, etc)
    private Creature activeCreature;
    private int numAdventurerAbilityCardsToExhaust = 1;

    public Game(){

    }

    public void init(Difficulty difficulty){
        // Build creature deck
        switch (difficulty){
            case EASY:
                buildDeck(1);
                break;
            case NORMAL:
                buildDeck(2);
                break;
            case HARD:
                buildDeck(3);
                break;
            case INSANE:
                buildDeck(4);
                break;
        }

        // Build locations
        for (int i = 0; i < 8; ++i){
            if (!locationDeck.getNormalLocations().isEmpty())
                locations.add(locationDeck.getNormalLocations().remove(0));
        }
        locations.add(locationDeck.getFinalLocations().remove(0));
        locationDeck.formUnusedLocationDeck();
        currentLocation = locations.remove(0);

        // Add unhallowed to horde
        horde.addToBottom(unhallowedDeck.draw());
    }

    /**
     * Randomly select 28 cards + 2 Acolytes
     */
    public void buildDeck(int numSummon){
        creatureDeck.shuffle();
        while (creatureDeck.size() > 28)
            creatureDeck.pop();
        creatureDeck.add(new Acolyte());
        creatureDeck.add(new Acolyte2());
        creatureDeck.shuffle();

        int cardsInEachPile = creatureDeck.size() / numSummon;
        for (int i = numSummon - 1; i >= 0; --i){
            int index = (i * cardsInEachPile) + Util.randomInt(cardsInEachPile);
            creatureDeck.add(index, new Summon());
        }

        unhallowedDeck.shuffle();
        while (unhallowedDeck.size() > 8)
            unhallowedDeck.pop();
    }

    public Phase getPhase() {
        return phase;
    }

    public PhaseStep getPhaseStep() {
        return phaseStep;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
        this.phaseStep = PhaseStep.START_PHASE;
    }

    public void setPhaseStep(PhaseStep phaseStep) {
        this.phaseStep = phaseStep;
    }

    public List<Adventurer> getAdventurers() {
        return adventurers;
    }

    public List<Creature> getLine() {
        return line;
    }

    public CreatureDeck getCreatureDeck() {
        return creatureDeck;
    }

    public int getFirewoodGainedFromExhaustingAdventurerCard() {
        return firewoodGainedFromExhaustingAdventurerCard;
    }

    public void setFirewoodGainedFromExhaustingAdventurerCard(int firewoodGainedFromExhaustingAdventurerCard) {
        this.firewoodGainedFromExhaustingAdventurerCard = firewoodGainedFromExhaustingAdventurerCard;
    }

    public int getFirewood() {
        return firewood;
    }

    public void setFirewood(int firewood) {
        this.firewood = firewood;
    }

    public void adjFirewood(int amount){
        this.firewood += amount;
        if (this.firewood < 0)
            this.firewood = 0;
        if (this.firewood > 15)
            this.firewood = 15;
    }

    public int getNumCreaturesToReveal(){
        if (firewood >= 1 && firewood <= 6)
            return 1;
        if (firewood >= 7 && firewood <= 11)
            return 2;
        if (firewood >= 12)
            return 3;
        return 0;
    }

    public Location getCurrentLocation(){
        return currentLocation;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public int getAdventurerRangeBonus() {
        return adventurerRangeBonus;
    }

    public void setAdventurerRangeBonus(int adventurerRangeBonus) {
        this.adventurerRangeBonus = adventurerRangeBonus;
    }

    public int getAdventurerIncreaseLowestDieRollBy() {
        return adventurerIncreaseLowestDieRollBy;
    }

    public void setAdventurerIncreaseLowestDieRollBy(int adventurerIncreaseLowestDieRollBy) {
        this.adventurerIncreaseLowestDieRollBy = adventurerIncreaseLowestDieRollBy;
    }

    public boolean isSendDefeatedUndeadToBottomOfCreatureDeck() {
        return sendDefeatedUndeadToBottomOfCreatureDeck;
    }

    public void setSendDefeatedUndeadToBottomOfCreatureDeck(boolean sendDefeatedUndeadToBottomOfCreatureDeck) {
        this.sendDefeatedUndeadToBottomOfCreatureDeck = sendDefeatedUndeadToBottomOfCreatureDeck;
    }

    public boolean isChooseUnhallowed() {
        return chooseUnhallowed;
    }

    public void setChooseUnhallowed(boolean chooseUnhallowed) {
        this.chooseUnhallowed = chooseUnhallowed;
    }

    public int getNumCardsRefreshedInCamp() {
        return numCardsRefreshedInCamp;
    }

    public void setNumCardsRefreshedInCamp(int numCardsRefreshedInCamp) {
        this.numCardsRefreshedInCamp = numCardsRefreshedInCamp;
    }

    public int getForestCreatureHealthBonus() {
        return forestCreatureHealthBonus;
    }

    public void setForestCreatureHealthBonus(int forestCreatureHealthBonus) {
        this.forestCreatureHealthBonus = forestCreatureHealthBonus;
    }

    public int getCreaturesRevealedModification() {
        return creaturesRevealedModification;
    }

    public void setCreaturesRevealedModification(int creaturesRevealedModification) {
        this.creaturesRevealedModification = creaturesRevealedModification;
    }

    public int getFirewoodReductionWhenFirstPositionPowerActivated() {
        return firewoodReductionWhenFirstPositionPowerActivated;
    }

    public void setFirewoodReductionWhenFirstPositionPowerActivated(int firewoodReductionWhenFirstPositionPowerActivated) {
        this.firewoodReductionWhenFirstPositionPowerActivated = firewoodReductionWhenFirstPositionPowerActivated;
    }

    public CreatureDeck getHorde() {
        return horde;
    }

    public CreatureDeck getGraveyard() {
        return graveyard;
    }

    public CreatureDeck getUnhallowedDeck() {
        return unhallowedDeck;
    }

    public LocationDeck getLocationDeck() {
        return locationDeck;
    }

    public Creature getActiveCreature() {
        return activeCreature;
    }

    public void setActiveCreature(Creature activeCreature) {
        this.activeCreature = activeCreature;
    }

    public int getNumAdventurerAbilityCardsToExhaust() {
        return numAdventurerAbilityCardsToExhaust;
    }

    public void setNumAdventurerAbilityCardsToExhaust(int numAdventurerAbilityCardsToExhaust) {
        this.numAdventurerAbilityCardsToExhaust = numAdventurerAbilityCardsToExhaust;
    }

    public int getFirewoodReductionWhenSendingToGraveyard() {
        return firewoodReductionWhenSendingToGraveyard;
    }

    public void setFirewoodReductionWhenSendingToGraveyard(int firewoodReductionWhenSendingToGraveyard) {
        this.firewoodReductionWhenSendingToGraveyard = firewoodReductionWhenSendingToGraveyard;
    }
}
