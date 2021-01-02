package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.Expression.ValueExpression;
import Model.ProgramState.ProgramState;
import Model.Types.*;
import Model.Values.Boolean;
import Model.Values.Integer;
import Model.Values.Ref;
import Model.Values.VString;

public class VariableDeclaration implements Statement{
    private final String name;
    private final Type type;

    public VariableDeclaration(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        var symbols = state.getSymbolTable();

        if(!symbols.defined(name)){
            if(type instanceof TNumber){
                symbols.set(name, new Integer(0));
            }
            if(type instanceof TBool){
                symbols.set(name, new Boolean(false));
            }
            if(type instanceof TString){
                symbols.set(name, new VString(""));
            }
            if(type instanceof TRef){
                symbols.set(name, ((TRef) type).getDefault());
            }
        }else{
            throw new RuntimeException("Variable already declared!");
        }
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        typeEnv.set(name, type);
        return typeEnv;
    }

    public String toString(){
        return type.toString() + " " + name;
    }
}
