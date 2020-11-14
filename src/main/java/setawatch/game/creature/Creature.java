package setawatch.game.creature;

import setawatch.game.Dice;
import setawatch.game.Game;
import setawatch.view.ImageUtil;
import setawatch.view.View;

import java.awt.*;

public abstract class Creature {
    public static final int CREATURE_CARD_WIDTH = 150;
    protected Image image;
    protected String name;
    protected CreatureType type;
    protected int baseHealth;
    protected int attack;

    protected boolean revealed = false;
    protected Dice stolenDice;

    protected int healthMultiplier = 1;
    protected int healthBonus = 0;
    protected boolean directAttackAllowed = true;
    protected boolean indirectAttackAllowed = true; // ie. Ability Card attacks

    public Creature(String name, CreatureType type, int attack, int baseHealth, String imageFilename){
        this.name = name;
        this.type = type;
        this.baseHealth = baseHealth;
        this.attack = attack;
        this.image = ImageUtil.get(imageFilename, CREATURE_CARD_WIDTH);
        this.revealed = false;
    }

    public void onReveal(Game game, View view){
        game.setActiveCreature(this);
        setRevealed(true);
    }

    public void onDefeat(Game game, View view){
        game.setActiveCreature(this);
    }

    public void onGoing(Game game, View view){
        game.setActiveCreature(this);
    }

    public void onFirstPosition(Game game, View view){
        game.setActiveCreature(this);
    }

    public void onEndOfRound(Game game, View view){
        game.setActiveCreature(this);
    }

    public String getName() {
        return name;
    }

    public CreatureType getType() {
        return type;
    }

    public int getHealth() {
        return (getBaseHealth() * healthMultiplier) + healthBonus;
    }

    public int getBaseHealth(){ return baseHealth; }

    public int getAttack() {
        return attack;
    }

    public Image getImage() {
        return image;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public Dice getStolenDice() {
        return stolenDice;
    }

    public void setStolenDice(Dice stolenDice) {
        this.stolenDice = stolenDice;
    }

    public void setHealthMultiplier(int healthMultiplier) {
        this.healthMultiplier = healthMultiplier;
    }

    public int getHealthBonus() {
        return healthBonus;
    }

    public void setHealthBonus(int healthBonus) {
        this.healthBonus = healthBonus;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public boolean isDirectAttackAllowed() {
        return directAttackAllowed;
    }

    public void setDirectAttackAllowed(boolean directAttackAllowed) {
        this.directAttackAllowed = directAttackAllowed;
    }

    public boolean isIndirectAttackAllowed() {
        return indirectAttackAllowed;
    }

    public void setIndirectAttackAllowed(boolean indirectAttackAllowed) {
        this.indirectAttackAllowed = indirectAttackAllowed;
    }
}
