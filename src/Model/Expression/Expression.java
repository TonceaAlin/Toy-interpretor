package Model.Expression;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.Type;
import Model.Values.Value;

public interface Expression {
    Value evaluate(SymbolTable symbolTable, Heap heap);
    Type typeChecker(Dict2<String, Type> typeEnv)throws TypeException;
}
