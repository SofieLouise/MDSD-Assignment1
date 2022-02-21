package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.List;

public class MachineInterpreter {

    private Machine machine;

    public void run(Machine m) {
        this.machine = m;
    }

    public State getCurrentState() {
        return machine.getCurrentState();
    }

    public void processEvent(String eventName) {
        List<Transition> transitions = getCurrentState().getTransitions().stream()
                .filter(transition -> transition.getEvent().equals(eventName))
                .toList();

        for (Transition transition : transitions) {
            if (machine.isConditionSatisfied(transition)) {
                machine.setCurrentState(transition.getTarget());
                machine.processTransition(transition);
                break;
            }
        }
    }

    public int getInteger(String variableName) {
        return machine.getInteger(variableName);
    }

}
