package Model.ProgramState;

import Model.ADTs.Dict2;
import Model.Values.Value;

import java.util.Map;
import java.util.Set;

public class SymbolTable {
    private final Dict2<String, Value> symbolTable;

    public SymbolTable() {
        this.symbolTable = new Dict2<String,Value>();
    }

    public SymbolTable(Dict2<String, Value> symbolTable){
        this.symbolTable = symbolTable;
    }
    public boolean defined(String id){
        return this.symbolTable.contains(id);
    }

    public Map<String, Value> getContent(){
        return symbolTable.getContent();
    }
    public void set(String id, Value value){
        this.symbolTable.set(id, value);
    }

    public Value get(String id){
        return this.symbolTable.get(id);
    }

    public SymbolTable copy(){
        return new SymbolTable(symbolTable.copy());
    }
    public String toString(){
        String all= "";
        for(var each: symbolTable.getAll()){
            all+= each.toString();
            all+= ", ";
        }
        return all;
    }

    public String toFile(){
        String result = "Symbol table: ";
        for(var each: symbolTable.getAll()){
            result += "\n" + each.toString();
        }
        return result;
    }
}
