package Model.Expression;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExpression implements Expression {

    private final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        return value;
    }

    @Override
    public Type typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        return value.getType();
    }
}
