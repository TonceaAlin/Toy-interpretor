package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.ProgramState;
import Model.ProgramState.SymbolTable;
import Model.Types.Type;


public class Fork implements Statement{
    private final Statement statement;

    public Fork(Statement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        SymbolTable copySymbolTable = state.getSymbolTable().copy();
        ExecutionStack newExecutionStack = new ExecutionStack();
        newExecutionStack.push(statement);
        ProgramState newProgramState = new ProgramState(newExecutionStack,copySymbolTable, state.getOutput(), state.getFileTable(), state.getHeap());
        newProgramState.setId();
        return newProgramState;

    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
         statement.typeChecker(typeEnv.copy());
         return typeEnv;
    }

    public String toString(){
        return "Fork(" + statement.toString() + ")";
    }
}
