package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

public class Print implements Statement{
    private final Expression expression;

    public Print(Expression expression){
        this.expression = expression;
    }


    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        state.getOutput().print(value);
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        expression.typeChecker(typeEnv);
        return typeEnv;
    }

    public String toString(){
        return "print(" + expression.toString() + ")";
    }
}
