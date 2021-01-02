package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.FileException;
import Model.Exceptions.TypeException;
import Model.Expression.Expression;
import Model.ProgramState.FileTable;
import Model.ProgramState.Heap;
import Model.ProgramState.ProgramState;
import Model.Types.TString;
import Model.Types.Type;
import Model.Values.VString;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFile implements Statement {
    private final Expression expression;

    public CloseReadFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        Value value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if(!value.getType().equals(new TString())){
            throw new FileException("File path must be a string!");
        }
        FileTable fileTable = state.getFileTable();
        VString fileName = (VString)value;
        if(!fileTable.defined(fileName)){
            throw new FileException("File is not open");
        }
        BufferedReader br = fileTable.get(fileName);
        try{
            br.close();
        } catch (IOException e) {
            throw new FileException("File can't be closed!");
        }
        fileTable.remove(fileName);

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
        return "close(" + expression + ")";
    }
}
