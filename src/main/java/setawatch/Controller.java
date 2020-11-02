package setawatch;

import setawatch.game.Difficulty;
import setawatch.game.Phase;
import setawatch.game.PhaseStep;

import javax.swing.text.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void run(){
        while (true){
            switch (model.getGame().getPhase()){
                case SETUP:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
                            model.getGame().init(Difficulty.NORMAL);
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            model.getGame().setPhase(Phase.ROLL);
                            break;
                    }
                    break;
                case ENTER_LOCATION:
                    switch (model.getGame().getPhaseStep()){
                        case START_PHASE:
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
                            model.getGame().setPhaseStep(PhaseStep.END_PHASE);
                            break;
                        case END_PHASE:
                            model.getGame().getCurrentLocation().leaveCamp(model.getGame());
                            model.getGame().setPhase(Phase.ROLL);
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
