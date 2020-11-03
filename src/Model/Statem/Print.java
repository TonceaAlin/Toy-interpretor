package Model.Statem;

import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.Values.Value;

public class Print implements Statement{
    private final Expression expression;

    public Print(Expression expression){
        this.expression = expression;
    }


    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        Value value = expression.evaluate(state.getSymbolTable());
        state.getOutput().print(value);
        return state;
    }

    public String toString(){
        return "print(" + expression.toString() + ")";
    }
}
