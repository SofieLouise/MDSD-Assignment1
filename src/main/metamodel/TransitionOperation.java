package main.metamodel;

public class TransitionOperation {

    private final String variableName;
    private final Type type;
    private final int value;

    public TransitionOperation(String variableName, Type operationType, int value) {
        this.variableName = variableName;
        this.type = operationType;
        this.value = value;
    }

    public String getVariableName() {
        return variableName;
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public enum Type {SET, INCREMENT, DECREMENT}
}
