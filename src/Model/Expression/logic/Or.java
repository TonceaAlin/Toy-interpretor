package Model.Expression.logic;

import Model.Expression.Expression;
import Model.Expression.Logic;
import Model.ProgramState.SymbolTable;
import Model.Types.TBool;
import Model.Values.Boolean;
import Model.Values.Value;

public class Or extends Logic {
    private final Expression left;
    private final Expression right;

    public Or(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) {
        Value leftVal = left.evaluate(symbolTable);
        Value rightVal = right.evaluate(symbolTable);

        if(!leftVal.getType().equals(new TBool()) || !rightVal.getType().equals(new TBool())){
            throw new RuntimeException("bool not defined");
        }

        boolean result = ((Boolean)leftVal).getValue() || ((Boolean)rightVal).getValue();
        return new Boolean(result);
    }
}
