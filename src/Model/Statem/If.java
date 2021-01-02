package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.ProgramState;
import Model.Types.TBool;
import Model.Types.Type;
import Model.Values.Boolean;
import Model.Values.Value;

public class If implements Statement {

    private final Expression expression;
    private final Statement thenState;
    private final Statement elseState;

    public If(Expression expression, Statement thenState, Statement elseState) {
        this.expression = expression;
        this.thenState = thenState;
        this.elseState = elseState;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        ExecutionStack stack = state.getExecutionStack();
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (value.getType().equals(new TBool())){
            Boolean bool = (Boolean) value;
            if(bool.getValue()){
                stack.push(thenState);
            }else{
                stack.push(elseState);
            }
        }
        else{
            throw new RuntimeException("Boolean expected in if clause");
        }
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typeExpr = expression.typeChecker(typeEnv);
        if(typeExpr.equals(new TBool())){
            thenState.typeChecker(typeEnv.copy());
            elseState.typeChecker(typeEnv.copy());
            return typeEnv;
        }else{
            throw new TypeException("Condition in IF must be a boolean");
        }
    }

    public String toString(){
        return "if (" + expression.toString() + ") then ( " + thenState.toString() + ") else (" + elseState.toString();

    }
}
