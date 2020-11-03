package Model.ProgramState;

import Model.ADTs.Dict2;
import Model.Values.Value;

public class SymbolTable {
    private final Dict2<String, Value> symbolTable;

    public SymbolTable() {
        this.symbolTable = new Dict2<String,Value>();
    }

    public boolean defined(String id){
        return this.symbolTable.contains(id);
    }

    public void set(String id, Value value){
        this.symbolTable.set(id, value);
    }

    public Value get(String id){
        return this.symbolTable.get(id);
    }

    public String toString(){
        String all= "-";
        for(var each: symbolTable.getAll()){
            all+= each.toString();
            all+= ", ";
        }
        return all;
    }
}
