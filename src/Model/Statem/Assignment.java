package Model.Statem;

import Model.Exceptions.VariableNotDeclaredException;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Values.Value;

public class Assignment implements Statement {
    private final String id;
    private final Expression expression;

    public Assignment(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }


    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        SymbolTable symbolTable = state.getSymbolTable();
        if(symbolTable.defined(id)){
            Value value = expression.evaluate(symbolTable);
            if (value.getType().equals(symbolTable.get(id).getType())){
                symbolTable.set(id, value);
            }else{
                throw new RuntimeException("Mismatch between type of variable and expression");
            }
        }else{
            throw new VariableNotDeclaredException(String.format("Variable '%s' not declared", id));
        }
        return state;
    }

    public String toString(){
        return id + " = " + expression.toString();
    }
}
