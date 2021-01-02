package Model.Expression;

import Model.ADTs.Dict2;
import Model.Exceptions.RelationalException;
import Model.Exceptions.TypeException;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.TBool;
import Model.Types.TNumber;
import Model.Types.Type;
import Model.Values.Boolean;
import Model.Values.Integer;
import Model.Values.Value;

public class RelationalExpression implements Expression{
    private final Expression expression1;
    private final Expression expression2;
    private final String opp;

    public RelationalExpression(Expression expression1, Expression expression2, String opp) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.opp = opp;
    }
    public String toString(){
        return expression1 + " " + opp + " " + expression2;
    }
    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        Value v1 = expression1.evaluate(symbolTable, heap);
        if(!v1.getType().equals(new TNumber())){
            throw new RelationalException("opperand 1 is not int");
        }
        Value v2 = expression2.evaluate(symbolTable, heap);
        if(!v2.getType().equals(new TNumber())){
            throw new RelationalException("opperand 2 is not int");
        }

        return switch (opp) {
            case "==" -> new Boolean(((Integer) v1).getValue() == ((Integer) v2).getValue());
            case "<" -> new Boolean(((Integer) v1).getValue() < ((Integer) v2).getValue());
            case ">" -> new Boolean(((Integer) v1).getValue() > ((Integer) v2).getValue());
            case ">=" -> new Boolean(((Integer) v1).getValue() >= ((Integer) v2).getValue());
            case "<=" -> new Boolean(((Integer) v1).getValue() <= ((Integer) v2).getValue());
            case "!=" -> new Boolean(((Integer) v1).getValue() != ((Integer) v2).getValue());
            default -> new Boolean(false);
        };
    }

    @Override
    public Type typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typ1, typ2;
        typ1 = expression1.typeChecker(typeEnv);
        typ2 = expression2.typeChecker(typeEnv);

        if(typ1.equals(new TNumber())){
            if(typ2.equals(new TNumber())){
                return new TBool();
            }else{
                throw new TypeException("second opperand must be an integer");
            }
        }else{
            throw new TypeException("first opperand must be an integer");
        }
    }

}
