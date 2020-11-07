package setawatch;

import setawatch.game.Difficulty;
import setawatch.game.Game;
import setawatch.game.Phase;
import setawatch.game.PhaseStep;
import setawatch.game.adventurer.beastmaster.BeastMaster;
import setawatch.game.adventurer.cleric.Cleric;
import setawatch.game.adventurer.dwarf.Dwarf;
import setawatch.game.adventurer.elfranger.ElfRanger;
import setawatch.game.creature.Creature;
import setawatch.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.logging.Logger;

public class Controller {
    private Logger logger = Logger.getLogger(Controller.class.getName());

    private Model model;
    private View view;

    private boolean campPlayerAssigned = false;

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
                            model.getGame().getHeroes().add(new BeastMaster());
                            model.getGame().getHeroes().add(new ElfRanger());
                            model.getGame().getHeroes().add(new Cleric());
                            model.getGame().getHeroes().add(new Dwarf());
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                            //return;
                        case END_PHASE:
                            view.init();
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
                            model.getGame().getHeroes().stream().forEach(h -> {
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
                            campPlayerAssigned = false;
                            break;
                        case CAMP_CHOOSE_PLAYER:
                            // TODO Choose player to go to camp
                            if (campPlayerAssigned) {
                                model.getGame().setPhaseStep(PhaseStep.CAMP_ASSIGN_DICE);
                                break;
                            }
                            return;
                        case CAMP_ASSIGN_DICE:
                            // TODO Allow camp player to take actions
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            return;
                        case END_PHASE:
                            model.getGame().setPhase(Phase.WATCH);
                            break;
                    }
                    break;
                case WATCH:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            logger.info("Starting WATCH Phase");
                            model.getGame().setPhaseStep(PhaseStep.WATCH_REVEAL_CREATURES);
                            break;
                        case WATCH_REVEAL_CREATURES:
                            // Reveal creatures in the line
                            int numCreaturesToReveal = model.getGame().getNumCreaturesToReveal();
                            while (model.getGame().getLine().stream().filter(c -> c.isRevealed()).count() < numCreaturesToReveal){
                                Optional<Creature> opt = model.getGame().getLine().stream().filter(c -> !c.isRevealed()).findFirst();
                                if (opt.isPresent()){
                                    opt.get().setRevealed(true);
                                    opt.get().onReveal(model.getGame());
                                }
                            }
                            model.getGame().setPhaseStep(PhaseStep.WATCH_ASSIGN_DICE);
                            break;
                        case WATCH_ASSIGN_DICE:
                            // TODO Allow players to assign dice
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            model.getGame().getCurrentLocation().leaveCamp(model.getGame());
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
        if (model.getGame().getHeroes().stream().allMatch(h -> h.getAssignedHeroAbilities().stream().allMatch(a -> a.isExhausted()))){
            model.getGame().setPhase(Phase.GAME_OVER);
            // TODO Notify player that they lost
        }

        if (model.getGame().getLocations().size() == 1 && model.getGame().getHorde().isEmpty() && model.getGame().getLine().isEmpty()){
            model.getGame().setPhase(Phase.GAME_OVER);
            // TODO Notify player that they won
        }
    }

}
