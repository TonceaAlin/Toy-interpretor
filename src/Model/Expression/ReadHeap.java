package Model.Expression;

import Model.ADTs.Dict2;
import Model.Exceptions.HeapException;
import Model.Exceptions.TypeException;
import Model.ProgramState.Heap;
import Model.ProgramState.SymbolTable;
import Model.Types.TRef;
import Model.Types.Type;
import Model.Values.Ref;
import Model.Values.Value;

public class ReadHeap implements Expression{
    private final Expression expression;


    public ReadHeap(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(SymbolTable symbolTable, Heap heap) {
        Value evaluatedExpression = expression.evaluate(symbolTable, heap);

        if(!(evaluatedExpression.getType() instanceof TRef)){
            throw new HeapException("expression must be evaluated to a Reference Value");
        }
        int address = ((Ref)evaluatedExpression).getAddress();
        if(!heap.defined(address)){
            throw new HeapException("expression not defined!");
        }
        Value value = heap.get(address);
        return value;
    }

    @Override
    public Type typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type type = expression.typeChecker(typeEnv);
        if(type instanceof TRef){
            TRef reft = (TRef)type;
            return reft.getInner();
        }else{
            throw new TypeException("argument must be a reference(TRef)");
        }
    }

    public String toString(){
        return "ReadHeap(" + expression.toString() + ")";

    }
}
