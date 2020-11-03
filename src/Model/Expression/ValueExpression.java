package Model.Expression;

import Model.ProgramState.SymbolTable;
import Model.Values.Value;

public class ValueExpression implements Expression {

    private final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
