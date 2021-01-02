package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.Heap;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Types.TBool;
import Model.Types.Type;
import Model.Values.Boolean;
import Model.Values.Value;

public class While implements Statement {
    private final Expression expression;
    private final Statement statement;

    public While(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        ExecutionStack executionStack = state.getExecutionStack();
        SymbolTable symbolTable = state.getSymbolTable();
        Heap heap = state.getHeap();
        Value value = expression.evaluate(symbolTable, heap);
        if(!value.getType().equals(new TBool())){
            throw new RuntimeException("expression must be a boolean!");
        }
        if(((Boolean)value).getValue()){
            executionStack.push(this);
            executionStack.push(statement);
        }
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type type = expression.typeChecker(typeEnv);
        if(type.equals(new TBool())){
            statement.typeChecker(typeEnv.copy());
            return typeEnv;
        }else{
            throw new TypeException("expression must be boolean in a while statement");
        }
    }

    public String toString(){
        return "(while (" + expression.toString() + ")" + statement.toString() + ")";
    }
}
