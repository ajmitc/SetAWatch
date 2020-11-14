package setawatch;

import setawatch.game.*;
import setawatch.game.adventurer.Adventurer;
import setawatch.game.adventurer.AdventurerAbility;
import setawatch.game.adventurer.beastmaster.BeastMaster;
import setawatch.game.adventurer.cleric.Cleric;
import setawatch.game.adventurer.dwarf.Dwarf;
import setawatch.game.adventurer.elfranger.ElfRanger;
import setawatch.game.creature.Creature;
import setawatch.game.location.Location;
import setawatch.view.ChooseLocationDialog;
import setawatch.view.GamePanel;
import setawatch.view.PopupUtil;
import setawatch.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Controller extends MouseAdapter{
    private Logger logger = Logger.getLogger(Controller.class.getName());

    private Model model;
    private View view;

    private Adventurer campAdventurer = null;
    private Map<CampAction, Dice> campActionsTaken = new HashMap<>();

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;

        this.view.getMainMenuPanel().getBtnNewGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setGame(new Game());
                view.showGame();
                run();
            }
        });

        this.view.getGamePanel().addMouseListener(this);
        this.view.getGamePanel().addMouseMotionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (model.getGame().getPhaseStep() == PhaseStep.CAMP_CHOOSE_PLAYER){
            Adventurer selected = view.getGamePanel().getSelectedAdventurer(e.getX(), e.getY());
            if (selected == null)
                return;
            if (selected.getNumTimesAtCamp() >= 2){
                PopupUtil.popupNotification(view.getFrame(), "Choose Player", "The " + selected.getName() + " has already been at camp " + selected.getNumTimesAtCamp() + " times.  Select a different Adventurer.");
                return;
            }
            selected.setAtCamp(true);
            selected.adjNumTimesAtCamp(1);
            campAdventurer = selected;
            run();
        }
        else if (model.getGame().getPhaseStep() == PhaseStep.CAMP_HEAL_CHOOSE_ABILITY_CARD){
            Adventurer selected = view.getGamePanel().getSelectedAdventurer(e.getX(), e.getY());
            if (selected == null)
                return;
            AdventurerAbility abilityCard = view.getGamePanel().getSelectedAdventurerAbility(selected, e.getX(), e.getY());
            if (abilityCard == null)
                return;
            abilityCard.setExhausted(false);
            model.getGame().setPhaseStep(PhaseStep.CAMP_ASSIGN_DICE);
            run();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (model.getGame().getPhaseStep() == PhaseStep.CAMP_ASSIGN_DICE){
            Adventurer selectedAdventurer = view.getGamePanel().getSelectedAdventurer(e.getX(), e.getY());
            if (selectedAdventurer == null || !selectedAdventurer.isAtCamp()){
                return;
            }
            Dice dice = view.getGamePanel().getSelectedDice(selectedAdventurer, e.getX(), e.getY());
            if (dice == null || dice.isAssigned())
                return;
            view.getGamePanel().setDiceDragging(dice, e.getX(), e.getY());
            //logger.info("Dragging dice (" + dice.getAdventurer().getName() + "/" + dice.getValue() + ") at [" + e.getX() + ", " + e.getY() + "]");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (model.getGame().getPhaseStep() == PhaseStep.CAMP_ASSIGN_DICE){
            if (view.getGamePanel().getDiceDragging() != null) {
                // Check that dice is over valid action area
                CampAction campAction = view.getGamePanel().getSelectedCampAction(e.getX(), e.getY());
                if (campAction == null){
                    logger.info("Invalid camp action");
                    view.getGamePanel().setDiceDragging(null, 0, 0);
                    return;
                }
                if (!canAssignDiceToCampAction(campAction, view.getGamePanel().getDiceDragging())){
                    PopupUtil.popupNotification(view.getFrame(), "Assign Dice", "Cannot take that camp action (already taken or invalid dice)");
                    view.getGamePanel().setDiceDragging(null, 0, 0);
                    view.refresh();
                    return;
                }
                logger.info("Dice dropped on camp action " + campAction);
                Dice dragging = view.getGamePanel().getDiceDragging();
                dragging.setAssigned(true);
                dragging.setCoord(view.getGamePanel().getDiceDraggingX() - (dragging.getImage().getWidth(null) / 2), view.getGamePanel().getDiceDraggingY() - (dragging.getImage().getHeight(null) / 2));
                //logger.info("Set Dice coord to [" + view.getGamePanel().getDiceDragging().getPx() + ", " + view.getGamePanel().getDiceDragging().getPy() + "]");

                // Execute selected action
                executeCampAction(campAction);

                view.getGamePanel().setDiceDragging(null, 0, 0);
                run();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (model.getGame().getPhaseStep() == PhaseStep.CAMP_ASSIGN_DICE){
            if (view.getGamePanel().getDiceDragging() != null) {
                view.getGamePanel().setDiceDragging(view.getGamePanel().getDiceDragging(), e.getX(), e.getY());
                //logger.info("Set dragging dice coord to [" + e.getX() + ", " + e.getY() + "]");
                view.getGamePanel().refresh();
            }
        }
    }

    public void run(){
        while (true){
            view.refresh();
            switch (model.getGame().getPhase()){
                case SETUP:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            logger.info("Starting SETUP Phase");
                            model.getGame().init(Difficulty.NORMAL);
                            model.getGame().setPhaseStep(PhaseStep.SETUP_CHOOSE_ADVENTURERS);
                            break;
                        case SETUP_CHOOSE_ADVENTURERS:
                            // TODO Let player choose adventurers
                            model.getGame().getAdventurers().add(new BeastMaster());
                            model.getGame().getAdventurers().add(new ElfRanger());
                            model.getGame().getAdventurers().add(new Cleric());
                            model.getGame().getAdventurers().add(new Dwarf());
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                            //return;
                        case END_PHASE:
                            view.initGame();
                            model.getGame().setPhase(Phase.ENTER_LOCATION);
                            break;
                    }
                    break;
                case ENTER_LOCATION:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            logger.info("Starting ENTER_LOCATION Phase");
                            if (model.getGame().getLocations().size() < 9){
                                // NOT first round, adjust firewood
                                model.getGame().adjFirewood(model.getGame().getCurrentLocation().getFirewoodChange());
                            }
                            model.getGame().getCurrentLocation().makeCamp(model.getGame());
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            model.getGame().setPhase(Phase.ROLL);
                            break;
                    }
                    break;
                case ROLL:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            logger.info("Starting ROLL Phase");
                            model.getGame().getAdventurers().stream().forEach(h -> {
                                h.getDice().stream().forEach(d -> {
                                    d.roll();
                                });
                            });
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            model.getGame().setPhase(Phase.CAMP);
                            break;
                    }
                    break;
                case CAMP:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            logger.info("Starting CAMP Phase");
                            model.getGame().setPhaseStep(PhaseStep.CAMP_CHOOSE_PLAYER);
                            PopupUtil.popupNotification(view.getFrame(), "Choose Player", "Choose an Adventurer to stay in camp");
                            campAdventurer = null;
                            break;
                        case CAMP_CHOOSE_PLAYER:
                            // Choose player to go to camp
                            if (campAdventurer != null) {
                                model.getGame().setPhaseStep(PhaseStep.CAMP_ASSIGN_DICE);
                                PopupUtil.popupNotification(view.getFrame(), "Assign Dice", "Assign the camp adventurer's dice to camp actions");
                                break;
                            }
                            return;
                        case CAMP_ASSIGN_DICE:
                            // Allow camp player to take actions
                            boolean allDiceAssigned = campAdventurer.getDice().stream().allMatch(d -> d.isAssigned());
                            if (!allDiceAssigned) {
                                return;
                            }
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            model.getGame().setPhase(Phase.WATCH);
                            break;
                    }
                    break;
                case WATCH:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            logger.info("Starting WATCH Phase");
                            // Any adventurers that have all ability cards exhausted cannot participate in Watch phase
                            model.getGame().getAdventurers().stream().forEach(a -> a.setAtWatch(!a.isAtCamp() && !a.getAssignedHeroAbilities().stream().allMatch(ab -> ab.isExhausted())));
                            model.getGame().setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES);
                            break;
                        case WATCH_REVEAL_CREATURES:
                            // Setup the Line
                            for (int i = 0; i < model.getGame().getCurrentLocation().getLineSize(); ++i){
                                Creature creature = model.getGame().getCreatureDeck().draw();
                                creature.setRevealed(false);
                                model.getGame().getLine().add(creature);
                            }
                            view.getGamePanel().refresh();
                            // Reveal creatures in the line
                            int numCreaturesToReveal = model.getGame().getNumCreaturesToReveal();
                            while (model.getGame().getLine().stream().filter(c -> c.isRevealed()).count() < numCreaturesToReveal){
                                Optional<Creature> opt = model.getGame().getLine().stream().filter(c -> !c.isRevealed()).findFirst();
                                if (opt.isPresent()){
                                    opt.get().setRevealed(true);
                                    view.getGamePanel().refresh();
                                    opt.get().onReveal(model.getGame(), view);
                                    // If Creature changed the PhaseStep, exit this method and wait for user input
                                    if (model.getGame().getPhaseStep() != PhaseStep.WATCH_REVEAL_CREATURES)
                                        return;
                                }
                            }
                            model.getGame().setPhaseStep(PhaseStep.WATCH_ASSIGN_DICE);
                            break;
                        case WATCH_ASSIGN_DICE:
                            // Apply onGoing effects
                            for (Creature creature: model.getGame().getLine()){
                                creature.onGoing(model.getGame(), view);
                            }

                            // Allow players to assign dice
                            if (model.getGame().getAdventurers().stream().flatMap(a -> a.getDice().stream()).anyMatch(d -> !d.isAssigned())){
                                return;
                            }
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            // Call onEndOfRound on graveyard creatures.  This is mainly for Skeletons which will be added to the top of the creature deck
                            model.getGame().getGraveyard().getDeck().stream().forEach(c -> c.onEndOfRound(model.getGame(), view));

                            // Breakdown the camp (undo any rules set at beginning of round)
                            model.getGame().getCurrentLocation().leaveCamp(model.getGame());

                            // Reset the Adventurers
                            model.getGame().getAdventurers().stream().forEach(a -> {
                                a.setAtCamp(false);
                                a.getDice().stream().forEach(d -> d.setAssigned(false));
                                // Move dice back to player boards
                                Optional<GamePanel.AdventurerDisplay> adventurerDisplay =
                                        view.getGamePanel().getAdventurerDisplays().stream().filter(ad -> ad.getAdventurer() == a).findFirst();
                                if (adventurerDisplay.isPresent()){
                                    for (int i = 0; i < adventurerDisplay.get().getDiceBounds().size(); ++i){
                                        a.getDice().get(i).setCoord(adventurerDisplay.get().getDiceBounds().get(i).x, adventurerDisplay.get().getDiceBounds().get(i).y);
                                    }
                                }
                            });

                            // Clear camp actions
                            campActionsTaken.clear();

                            // Set next round start phase
                            model.getGame().setPhase(Phase.ROLL);
                            break;
                    }
                    break;
                case GAME_OVER:
                    return;
                default:
                    break;
            } // end switch(Phase)
            if (model.getGame().getPhase() != Phase.SETUP)
                checkGameOver();
        } // end while(true)
    } // end run()

    private void checkGameOver(){
        if (model.getGame().getAdventurers().stream().allMatch(h -> h.getAssignedHeroAbilities().stream().allMatch(a -> a.isExhausted()))){
            model.getGame().setPhase(Phase.GAME_OVER);
            // Notify player that they lost
            PopupUtil.popupNotification(view.getFrame(), "Game Over", "All Adventurers are exhausted.  You have lost!");
        }

        if (model.getGame().getLocations().size() == 1 && model.getGame().getHorde().isEmpty() && model.getGame().getLine().isEmpty()){
            model.getGame().setPhase(Phase.GAME_OVER);
            // Notify player that they won
            PopupUtil.popupNotification(view.getFrame(), "Game Over", "You win!");
        }
    }

    private boolean canAssignDiceToCampAction(CampAction campAction, Dice dice){
        if (campActionsTaken.keySet().contains(campAction))
            return false;
        switch (campAction){
            case BOLSTER:
            case VANQUISH:
            case SEAL:
                // Check for multiple dice with same value
                // TODO This doesn't seem to be working
                for (Dice addice: dice.getAdventurer().getDice()){
                    // Only allow if dice is not assigned for assigned to Vanquish, Bolster, or Seal
                    if (addice != dice && addice.getValue() == dice.getValue() && (!dice.isAssigned() || isDiceAssignedToCampAction(dice, CampAction.VANQUISH, CampAction.SEAL, CampAction.BOLSTER)))
                        return true;
                }
                break;
            case SCOUT_AHEAD2:
                if (!campActionsTaken.keySet().contains(CampAction.SCOUT_AHEAD1))
                    return false;
                Dice scoutAhead1Dice = campActionsTaken.get(CampAction.SCOUT_AHEAD1);
                if (dice.getValue() <= scoutAhead1Dice.getValue()){
                    return false;
                }
                break;
            case SCOUT_AHEAD3:
                if (!campActionsTaken.keySet().contains(CampAction.SCOUT_AHEAD1) || !campActionsTaken.keySet().contains(CampAction.SCOUT_AHEAD2))
                    return false;
                Dice scoutAhead2Dice = campActionsTaken.get(CampAction.SCOUT_AHEAD2);
                if (dice.getValue() <= scoutAhead2Dice.getValue()){
                    return false;
                }
                break;
            case HEAL:
                if (dice.getValue() != 6)
                    return false;
                break;
            case CHECK_MAP:
                if (dice.getValue() != 4)
                    return false;
                // Cannot do this if only final location left in map deck
                if (model.getGame().getLocations().size() <= 1)
                    return false;
                break;
            case SCOUT_AHEAD1:
            case CHOP_FIREWOOD1:
            case CHOP_FIREWOOD2:
            case CHOP_FIREWOOD3:
            case EQUIP:
                break;
        }
        return true;
    }

    private void executeCampAction(CampAction campAction){
        switch (campAction){
            case CHECK_MAP:
                doCheckMap();
                break;
            case HEAL:
                doHeal();
                break;
            case SEAL:
                doSeal();
                break;
            case EQUIP:
                doEquip();
                break;
            case BOLSTER:
                doBolster();
                break;
            case VANQUISH:
                doVanquish();
                break;
            case SCOUT_AHEAD1:
            case SCOUT_AHEAD2:
            case SCOUT_AHEAD3:
                doScoutAhead();
                break;
            case CHOP_FIREWOOD1:
            case CHOP_FIREWOOD2:
            case CHOP_FIREWOOD3:
                doChopFirewood();
                break;
        }
    }

    private void doCheckMap(){
        // Draw top location from map deck and top location from unused location deck
        Location location1 = model.getGame().getLocations().remove(0);
        Location location2 = model.getGame().getLocationDeck().getUnusedLocations().remove(0);
        // Allow player to choose which one to place back on top of map deck
        ChooseLocationDialog dialog = new ChooseLocationDialog(view.getFrame(), location1, location2);
        dialog.setVisible(true);
        Location selected = dialog.getSelected();
        model.getGame().getLocations().add(0, selected);
        // Put unpicked location on bottom of unused location deck
        if (location1 != selected)
            model.getGame().getLocationDeck().getUnusedLocations().add(location1);
        else
            model.getGame().getLocationDeck().getUnusedLocations().add(location2);
    }

    private void doEquip(){
        // TODO Swap an unused ability card with an assigned ability card
        // TODO exhausted state of new ability card is set to exhausted state of assigned ability card
    }

    private void doHeal(){
        // Let player choose one exhausted ability card from any adventurer
        model.getGame().setPhaseStep(PhaseStep.CAMP_HEAL_CHOOSE_ABILITY_CARD);
    }

    private void doChopFirewood(){
        model.getGame().adjFirewood(2);
        logger.info("Add +2 firewood");
    }

    private void doScoutAhead(){
        // TODO Draw and look at the top 2 Creatures of the Creature deck. For each card, return it either to the top or to the bottom of the Creatures deck.
    }

    private void doVanquish(){
        // TODO Ensure that at least 2 dice of same value are assigned to vanquish, seal, and/or bolster
        // Take the top card of the Horde and remove it from the game.
        model.getGame().getHorde().draw();
    }

    private void doSeal(){
        // TODO Ensure that at least 2 dice of same value are assigned to vanquish, seal, and/or bolster
        // TODO Search the Graveyard for an Unhallowed card of your choosing and move it to the bottom of the Unhallowed deck. Do not shuffle the Graveyard afterwards.
    }

    private void doBolster(){
        // TODO Ensure that at least 2 dice of same value are assigned to vanquish, seal, and/or bolster
        // TODO Each Adventurer on Watch may choose to reroll any number of their dice.
    }

    private boolean isDiceAssignedToCampAction(Dice dice, CampAction ... actions){
        for (CampAction action: actions){
            if (campActionsTaken.containsKey(action) && campActionsTaken.get(action) == dice)
                return true;
        }
        return false;
    }
}
