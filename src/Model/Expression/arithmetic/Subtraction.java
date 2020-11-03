package Model.Expression.arithmetic;

import Model.Expression.Arithmetic;
import Model.Expression.Expression;
import Model.ProgramState.SymbolTable;
import Model.Types.TNumber;
import Model.Values.Integer;
import Model.Values.Value;

public class Subtraction extends Arithmetic {
    private final Expression left;
    private final Expression right;

    public Subtraction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable) {
        Value leftVal = left.evaluate(symbolTable);
        Value rightVal = right.evaluate(symbolTable);

        if(!leftVal.getType().equals(new TNumber()) || !rightVal.getType().equals(new TNumber())){
            throw new RuntimeException("There is no integer");
        }
        int result = 0;
        result = ((Integer)leftVal).getValue() - ((Integer)rightVal).getValue();

        return new Integer(result);
    }

    public String toString(){
        return this.left.toString() + " - " + this.right.toString();
    }
}
