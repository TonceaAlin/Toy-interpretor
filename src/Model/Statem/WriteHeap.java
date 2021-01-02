package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.HeapException;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.Heap;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Types.TRef;
import Model.Types.Type;
import Model.Values.Ref;
import Model.Values.Value;

public class WriteHeap implements Statement{
    private final String variableName;
    private final Expression expression;

    public WriteHeap(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        SymbolTable symbolTable = state.getSymbolTable();
        Heap heap = state.getHeap();

        if(!symbolTable.defined(variableName)){
            throw new HeapException("variable not defined in symboltable!");
        }

        Value variableValue = symbolTable.get(variableName);
        if(!(variableValue instanceof Ref)){
            throw new HeapException("Variable is not a reference!");
        }

        Ref reference = (Ref)variableValue;
        if(!heap.defined(reference.getAddress())){
            throw new HeapException("undefined in the heap!");
        }

        Value value = expression.evaluate(symbolTable, heap);
        if(!value.getType().equals(reference.getLocationType())){
            throw new HeapException("expression is not a reference!");
        }
        heap.set(reference.getAddress(), value);
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typeVar = typeEnv.get(variableName);
        Type typeExp = expression.typeChecker(typeEnv);
        if(typeVar.equals(new TRef(typeExp))){
            return typeEnv;
        }else{
            throw new TypeException("different types for sides at writing on heap");
        }
    }

    public String toString(){
        return "WriteHeap(" + variableName + ", " + expression.toString() + ")";
    }
}
