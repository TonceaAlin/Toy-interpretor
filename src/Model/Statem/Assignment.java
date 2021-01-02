package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.Exceptions.VariableNotDeclaredException;
import Model.Expression.Expression;
import Model.ProgramState.Heap;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Types.Type;
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
        Heap heap = state.getHeap();
        if(symbolTable.defined(id)){
            Value value = expression.evaluate(symbolTable, heap);
            if (value.getType().equals(symbolTable.get(id).getType())){
                symbolTable.set(id, value);
            }else{
                throw new RuntimeException("Mismatch between type of variable and expression");
            }
        }else{
            throw new VariableNotDeclaredException(String.format("Variable '%s' not declared", id));
        }
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typeVar = typeEnv.get(id);
        Type typeExp = expression.typeChecker(typeEnv);
        if(typeVar.equals(typeExp)){
            return typeEnv;
        }else{
            throw new TypeException("Opperands must have the same type in an assignment");
        }
    }

    public String toString(){
        return id + " = " + expression.toString();
    }
}
