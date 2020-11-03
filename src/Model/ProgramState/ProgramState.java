package Model.ProgramState;
import Model.ADTs.Stack2;
import Model.Statem.Statement;
import Model.Values.Value;
import Model.ProgramState.*;
import java.util.*;

public class ProgramState {

    private final ExecutionStack executionStack;
    private final SymbolTable symbolTable;
    private final OutputStream output;

    public ProgramState(ExecutionStack stack, SymbolTable symbolTable, OutputStream output, Statement state){

        executionStack = stack;
        this.symbolTable = symbolTable;
        this.output = output;
        stack.push(state);
    }



    public ProgramState(Statement state) {
        this.executionStack = new ExecutionStack(state);
        this.symbolTable = new SymbolTable();
        this.output = new OutputStream();
    }


    public ExecutionStack getExecutionStack() { return this.executionStack;}
    public SymbolTable getSymbolTable() { return this.symbolTable;}
    public OutputStream getOutput() { return this.output;}

    public String toString(){
        return "exeStack: " + this.executionStack.toString() + "\nsymTable: " + this.symbolTable.toString() +
                "\noutput: " + this.output.toString();
    }
}
