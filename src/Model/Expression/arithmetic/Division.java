package Model.Expression.arithmetic;

import Model.ADTs.Dict2;
import Model.Exceptions.DivisionByZero;
import Model.Exceptions.TypeException;
import Model.Expression.Arithmetic;
import Model.Expression.Expression;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.TNumber;
import Model.Types.Type;
import Model.Values.Integer;
import Model.Values.Value;

public class Division extends Arithmetic {
    private final Expression left;
    private final Expression right;

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        Value leftVal = left.evaluate(symbolTable, heap);
        Value rightVal = right.evaluate(symbolTable, heap);

        if(!leftVal.getType().equals(new TNumber()) || !rightVal.getType().equals(new TNumber())){
            throw new RuntimeException("There is no integer");
        }
        int result = 1;
        if(((Integer)rightVal).getValue() == 0){
            throw new DivisionByZero("Division by zero");
        }
        result = ((Integer)leftVal).getValue() / ((Integer)rightVal).getValue();

        return new Integer(result);
    }

    @Override
    public Type typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typ1, typ2;
        typ1 = left.typeChecker(typeEnv);
        typ2 = right.typeChecker(typeEnv);
        if(typ1.equals(new TNumber())){
            if(typ2.equals(new TNumber())){
                return new TNumber();
            }else{
                throw new TypeException("second opperand must be an integer");
            }
        }else{
            throw new TypeException("first opperand must be an integer");
        }
    }
    public String toString(){
        return this.left.toString() + " / " + this.right.toString();
    }
}
