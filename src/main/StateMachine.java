package main;


import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;
import main.metamodel.TransitionBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachine {
    private final Map<String, State> states = new HashMap<>();
    private State current;
    private State initial;
    private final TransitionBuilder transitionBuilder = new TransitionBuilder();
    private final Map<String, Integer> integers = new HashMap<>();

    public Machine build() {
        return new Machine(initial, states.values(), integers);
    }

    public StateMachine state(String name) {
        current = getState(name);
        return this;
    }

    private State getState(String name) {
        if (!states.containsKey(name)) states.put(name, new State(name));
        return states.get(name);
    }

    public StateMachine initial() {
        initial = current;
        return this;
    }

    public StateMachine when(String event) {
        transitionBuilder.setEvent(event);
        return this;
    }

    public StateMachine to(String stateName) {
        transitionBuilder.setTarget(getState(stateName));
        current.addTransition(transitionBuilder.build());
        return this;
    }

    public StateMachine integer(String variableName) {
        integers.put(variableName, 0);
        return this;
    }

    public StateMachine set(String variableName, int value) {
        transitionBuilder.addSetOperation(variableName, value, getLastTransition());
        return this;
    }

    public StateMachine increment(String variableName) {
        transitionBuilder.addIncrementOperation(variableName, getLastTransition());
        return this;
    }

    public StateMachine decrement(String variableName) {
        transitionBuilder.addDecrementOperation(variableName, getLastTransition());
        return this;
    }

    public StateMachine ifEquals(String variableName, int value) {
        transitionBuilder.addEqualConditional(variableName, value, getLastTransition());
        return this;
    }

    public StateMachine ifGreaterThan(String variableName, int value) {
        transitionBuilder.addGreaterThanConditional(variableName, value, getLastTransition());
        return this;
    }

    public StateMachine ifLessThan(String variableName, int value) {
        transitionBuilder.addLessThanConditional(variableName, value, getLastTransition());
        return this;
    }

    private Transition getLastTransition() {
        List<Transition> transitions = current.getTransitions();
        return transitions.get(transitions.size() - 1);
    }
}
