package Model.Statem;

import Model.ProgramState.ProgramState;
import Model.Types.TBool;
import Model.Types.TNumber;
import Model.Types.Type;
import Model.Values.Boolean;
import Model.Values.Integer;

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
        }else{
            throw new RuntimeException("Variable already declared!");
        }
        return state;
    }

    public String toString(){
        return type.toString() + " " + name;
    }
}
