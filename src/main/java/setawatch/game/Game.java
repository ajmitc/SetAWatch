package setawatch.game;

import setawatch.game.creature.Creature;
import setawatch.game.creature.CreatureDeck;
import setawatch.game.adventurer.Adventurer;
import setawatch.game.location.Location;
import setawatch.game.location.LocationDeck;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Phase phase = Phase.SETUP;
    private PhaseStep phaseStep = PhaseStep.START_PHASE;

    private List<Adventurer> adventurers = new ArrayList<>();
    private List<Creature> line = new ArrayList<>();
    private CreatureDeck creatureDeck = new CreatureDeck();
    private List<Location> locations = new ArrayList<>();
    private LocationDeck locationDeck = new LocationDeck();
    private List<Creature> horde = new ArrayList<>();

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

    public Game(){

    }

    public void init(Difficulty difficulty){
        // Build creature deck
        switch (difficulty){
            case EASY:
                creatureDeck.buildDeck(1);
                break;
            case NORMAL:
                creatureDeck.buildDeck(2);
                break;
            case HARD:
                creatureDeck.buildDeck(3);
                break;
            case INSANE:
                creatureDeck.buildDeck(4);
                break;
        }

        // Build locations
        for (int i = 0; i < 8; ++i){
            if (!locationDeck.getNormalLocations().isEmpty())
                locations.add(locationDeck.getNormalLocations().remove(0));
        }
        locations.add(locationDeck.getFinalLocations().remove(0));
        locationDeck.formUnusedLocationDeck();

        // Add unhallowed to horde
        horde.add(creatureDeck.getUnhallowed().remove(0));
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

    public List<Adventurer> getHeroes() {
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
        return locations.get(0);
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

    public List<Creature> getHorde() {
        return horde;
    }
}
