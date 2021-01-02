package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.FileException;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.FileTable;
import Model.ProgramState.ProgramState;
import Model.Types.TString;
import Model.Types.Type;
import Model.Values.VString;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFile implements Statement {
    private final Expression expression;

    public OpenReadFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!value.getType().equals(new TString())){
            throw new FileException("this is not a string value");
        }
        VString fileName = (VString)value;
        FileTable fileTable = state.getFileTable();
        if(fileTable.defined(fileName)){
            throw new FileException("the file already exists");
        }
        try{
            BufferedReader file = new BufferedReader(new FileReader(fileName.getValue()));
            fileTable.add(fileName, file);
        } catch (FileNotFoundException e) {
            throw new FileException("can t open");
        }
        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        if(expression.typeChecker(typeEnv).equals(new TString())){
            return typeEnv;
        }else{
            throw new TypeException("File path expression must be a string");
        }
    }

    public String toString(){
        return "openFile( " + expression + ")";
    }
}
