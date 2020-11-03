package Model.Expression;

import Model.ProgramState.SymbolTable;
import Model.Values.Value;

public class VariableExpression implements Expression{
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) {
        return symbolTable.get(id);
    }

    public String toString(){
        return id;
    }
}
