package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.HeapException;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.Heap;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Types.TRef;
import Model.Types.Type;
import Model.Values.Ref;
import Model.Values.Value;

import java.util.Random;

public class New implements Statement{
    private final String name;
    private final Expression expression;

    public New(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        SymbolTable symbolTable = state.getSymbolTable();
        Heap heap = state.getHeap();
        if(!symbolTable.defined(name))
            throw new HeapException("variable not defined!");
        Value variable = symbolTable.get(name);
        if(!(variable.getType() instanceof TRef))
            throw new HeapException("not a reference!");

        Value expressionValue = expression.evaluate(symbolTable, heap);
        Type locationType = ((Ref)variable).getLocationType();

        if(!expressionValue.getType().equals(locationType)){
            throw new HeapException("Types not equal!");
        }
        Random random = new Random();
        int position = random.nextInt(100);
        if( position == 0 || heap.defined(position)){
            position = random.nextInt();
        }
        heap.set(position, expressionValue);
        symbolTable.set(name, new Ref(position, locationType));
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typeVar = typeEnv.get(name);
        Type typeExp = expression.typeChecker(typeEnv);
        if(typeVar.equals(new TRef(typeExp))){
            return typeEnv;
        }else{
            throw new TypeException("sides have different type");
        }
    }

    public String toString(){
        return "New(" + this.name + ", " + this.expression.toString() + ")";
    }
}
