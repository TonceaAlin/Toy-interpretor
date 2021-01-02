package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.FileException;
import Model.Exceptions.TypeException;
import Model.Exceptions.VariableNotDeclaredException;
import Model.Expression.Expression;
import Model.ProgramState.FileTable;
import Model.ProgramState.Heap;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Types.TNumber;
import Model.Types.TRef;
import Model.Types.TString;
import Model.Types.Type;
import Model.Values.Integer;
import Model.Values.VString;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements Statement{
    private final Expression expression;
    private final String name;

    public ReadFile(Expression expression, String name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        SymbolTable symbolTable = state.getSymbolTable();
        Heap heap = state.getHeap();
        if (!symbolTable.defined(name)){
            throw new VariableNotDeclaredException("Variable not in the symbol table");
        }
        if (!symbolTable.get(name).getType().equals(new TNumber())){
            throw new FileException("Variable declared but it's not an int!");
        }
        Value value = expression.evaluate(symbolTable, heap);
        if (!value.getType().equals(new TString())){
            throw new FileException("File path must be a string!");
        }

        FileTable fileTable = state.getFileTable();
        VString filename = (VString)value;
        if(!fileTable.defined(filename)){
            throw new FileException("This file is not open!");
        }
        BufferedReader br = fileTable.get(filename);

        int readValue=0;
        try{
            String line = br.readLine();
            if(line == null)
                readValue = 0;
            else{
                readValue = java.lang.Integer.parseInt(line);
            }
        } catch (IOException e) {
            throw new FileException("No things to read");
        }

        symbolTable.set(name, new Integer(readValue));
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        Type typeVar = typeEnv.get(name);
        Type typeExp = expression.typeChecker(typeEnv);
        if(typeVar.equals(new TNumber())){
            if(typeExp.equals(new TString())){
                return typeEnv;
            }else{
                throw new TypeException("expression not of type string");
            }
        }else{
            throw new TypeException("variable must be an integer");
        }
    }

    public String toString(){
        return "ReadFile( " + expression + "," + name + ")";
    }
}
