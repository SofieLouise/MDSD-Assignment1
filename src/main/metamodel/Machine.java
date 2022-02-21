package main.metamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Machine {

    private final List<State> states = new ArrayList<>();
    private final State initialState;
    private State currentState;
    private final Map<String, Integer> integers;

    public Machine(State initialState, Collection<State> states, Map<String, Integer> integers) {
        this.initialState = initialState;
        this.currentState = initialState;
        this.states.addAll(states);
        this.integers = integers;
    }

    public List<State> getStates() {
        return states;
    }

    public State getInitialState() {
        return initialState;
    }

    public State getState(String name) {
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    public int numberOfIntegers() {
        return integers.size();
    }

    public boolean hasInteger(String string) {
        return integers.containsKey(string);
    }

    public State getCurrentState() {
        return currentState;
    }

    public boolean isConditionSatisfied(Transition transition) {
        if (!transition.isConditional()) {
            return true;
        }

        String variableName = transition.getConditionVariableName();
        Integer currentValue = integers.get(variableName);

        int conditionComparedValue = transition.getConditionComparedValue();

        return switch (transition.getConditionType()) {
            case EQUAL -> currentValue == conditionComparedValue;
            case LESS_THAN -> currentValue < conditionComparedValue;
            case GREATER_THAN -> currentValue > conditionComparedValue;
        };
    }

    public void setCurrentState(State state) {
        currentState = state;
    }

    public void processTransition(Transition transition) {
        if (!transition.hasOperation()) {
            return;
        }
        String operationVariableName = transition.getOperationVariableName();
        if (!integers.containsKey(operationVariableName))
            throw new RuntimeException("Variable does not exist");

        switch (transition.getOperationType()) {
            case SET -> integers.replace(operationVariableName, transition.getOperationValue());
            case INCREMENT -> {
                Integer newIncrementValue = integers.get(operationVariableName) + transition.getOperationValue();
                integers.replace(operationVariableName, newIncrementValue);
            }
            case DECREMENT -> {
                Integer newDecrementValue = integers.get(operationVariableName) - transition.getOperationValue();
                integers.replace(operationVariableName, newDecrementValue);
            }
        }
    }

    public Integer getInteger(String variableName) {
        return integers.get(variableName);
    }
}
