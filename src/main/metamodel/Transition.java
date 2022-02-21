package main.metamodel;

public class Transition {
    private final String event;
    private final State targetState;
    private TransitionOperation transitionOperation;
    private TransitionCondition transitionCondition;

    public Transition(String event, State targetState) {
        this.event = event;
        this.targetState = targetState;
    }

    public Object getEvent() {
        return event;
    }

    public State getTarget() {
        return targetState;
    }

    public boolean hasSetOperation() {
        return transitionOperation.getType().equals(TransitionOperation.Type.SET);
    }

    public boolean hasIncrementOperation() {
        return transitionOperation.getType().equals(TransitionOperation.Type.INCREMENT);
    }

    public boolean hasDecrementOperation() {
        return transitionOperation.getType().equals(TransitionOperation.Type.DECREMENT);
    }

    public String getOperationVariableName() {
        return transitionOperation.getVariableName();
    }

    public boolean isConditional() {
        return transitionCondition != null;
    }

    public String getConditionVariableName() {
        return transitionCondition.getVariableName();
    }

    public Integer getConditionComparedValue() {
        return transitionCondition.getComparedValue();
    }

    public boolean isConditionEqual() {
        return transitionCondition.getType().equals(TransitionCondition.Type.EQUAL);
    }

    public boolean isConditionGreaterThan() {
        return transitionCondition.getType().equals(TransitionCondition.Type.GREATER_THAN);
    }

    public boolean isConditionLessThan() {
        return transitionCondition.getType().equals(TransitionCondition.Type.LESS_THAN);
    }

    public boolean hasOperation() {
        return transitionOperation != null;
    }

    public void setOperation(TransitionOperation transitionOperation) {
        this.transitionOperation = transitionOperation;
    }

    public void setCondition(TransitionCondition transitionCondition) {
        this.transitionCondition = transitionCondition;
    }

    public TransitionCondition.Type getConditionType() {
        return transitionCondition.getType();
    }

    public TransitionOperation.Type getOperationType() {
        return transitionOperation.getType();
    }

    public Integer getOperationValue() {
        return transitionOperation.getValue();
    }
}
