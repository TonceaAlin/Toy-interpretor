package Model.Expression;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.Type;
import Model.Values.Value;

public class VariableExpression implements Expression{
    private final String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        return symbolTable.get(id);
    }

    @Override
    public Type typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        return typeEnv.get(id);
    }

    public String toString(){
        return id;
    }
}
