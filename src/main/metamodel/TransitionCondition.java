package main.metamodel;

public class TransitionCondition {
    private final String variableName;
    private final Integer comparedValue;
    private final Type type;

    public TransitionCondition(String variableName, int value, Type type) {
        this.variableName = variableName;
        this.comparedValue = value;
        this.type = type;
    }

    public String getVariableName() {
        return variableName;
    }

    public Integer getComparedValue() {
        return comparedValue;
    }

    public Type getType() {
        return type;
    }

    enum Type {EQUAL, GREATER_THAN, LESS_THAN}
}
