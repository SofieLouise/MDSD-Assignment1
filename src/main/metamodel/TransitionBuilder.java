package main.metamodel;

public class TransitionBuilder {

    private String event;
    private State target;

    public Transition build() {
        Transition transition = new Transition(this.event, target);
        reset();
        return transition;
    }

    private void reset() {
        event = null;
        target = null;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setTarget(State target) {
        this.target = target;
    }

    public void addSetOperation(String variableName, int value, Transition transition) {
        transition.setOperation(new TransitionOperation(variableName, TransitionOperation.Type.SET, value));
    }

    public void addIncrementOperation(String variableName, Transition transition) {
        transition.setOperation(new TransitionOperation(variableName, TransitionOperation.Type.INCREMENT, 1));
    }

    public void addDecrementOperation(String variableName, Transition transition) {
        transition.setOperation(new TransitionOperation(variableName, TransitionOperation.Type.DECREMENT, 1));
    }

    public void addEqualConditional(String conditionVariableName, int amount, Transition transition) {
        transition.setCondition(new TransitionCondition(conditionVariableName, amount, TransitionCondition.Type.EQUAL));
    }

    public void addGreaterThanConditional(String conditionVariableName, int amount, Transition transition) {
        transition.setCondition(new TransitionCondition(conditionVariableName, amount, TransitionCondition.Type.GREATER_THAN));
    }

    public void addLessThanConditional(String conditionVariableName, int amount, Transition transition) {
        transition.setCondition(new TransitionCondition(conditionVariableName, amount, TransitionCondition.Type.LESS_THAN));
    }
}
