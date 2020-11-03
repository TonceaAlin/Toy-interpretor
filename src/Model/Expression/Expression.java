package Model.Expression;

import Model.ProgramState.SymbolTable;
import Model.Values.Value;

public interface Expression {
    Value evaluate(SymbolTable symbolTable);
}
