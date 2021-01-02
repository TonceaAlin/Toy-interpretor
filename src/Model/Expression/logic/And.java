package Model.Expression.logic;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.Expression.Logic;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.TBool;
import Model.Types.Type;
import Model.Values.Boolean;
import Model.Values.Value;

public class And extends Logic{
    private final Expression left;
    private final Expression right;
    public And(Expression left, Expression right){

        this.left = left;
        this.right = right;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        Value leftVal = left.evaluate(symbolTable, heap);
        Value rightVal = right.evaluate(symbolTable, heap);

        if(!leftVal.getType().equals(new TBool()) || !rightVal.getType().equals(new TBool())){
            throw new RuntimeException("bool not defined");
        }

        boolean result = ((Boolean)leftVal).getValue() && ((Boolean)rightVal).getValue();
        return new Boolean(result);
    }
    public String toString(){
        return left.toString() + " && " + right.toString();
    }

    @Override
    public Type typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typ1, typ2;
        typ1 = left.typeChecker(typeEnv);
        typ2 = right.typeChecker(typeEnv);

        if(typ1.equals(new TBool())){
            if(typ2.equals(new TBool())){
                return new TBool();
            }else{
                throw new TypeException("second opperand must be a boolean");
            }
        }else{
            throw new TypeException("first opperand must be a boolean");
        }
    }
}
