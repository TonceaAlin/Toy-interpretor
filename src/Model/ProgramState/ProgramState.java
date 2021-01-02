package Model.ProgramState;
import Model.ADTs.Dict2;
import Model.Exceptions.ExecutionStackEmpty;
import Model.Statem.Statement;
import Model.Types.Type;

import java.util.ArrayList;

public class ProgramState {

    private final ExecutionStack executionStack;
    private final SymbolTable symbolTable;
    private OutputStream output;
    private FileTable fileTable;
    private Heap heap;
    private static int lastId = 1;
    private int id;



    public synchronized void setId(){
        lastId += 1;
        id = lastId;
    }


    public ProgramState(Statement state) {
        this.executionStack = new ExecutionStack(state);
        this.fileTable = new FileTable();
        this.symbolTable = new SymbolTable();
        this.output = new OutputStream();
        this.id = 1;
        this.heap = new Heap();
    }

    public ProgramState(ExecutionStack stack, SymbolTable symbolTable, OutputStream output, FileTable fileTable, Heap heap,Statement state){

        executionStack = stack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = 1;
        stack.push(state);
    }

    public ProgramState(ExecutionStack newExecutionStack, SymbolTable copySymbolTable, OutputStream output, FileTable fileTable, Heap heap) {
        this.executionStack = newExecutionStack;
        this.symbolTable = copySymbolTable;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
    }



    public ProgramState oneStep() throws RuntimeException{
        if(executionStack.isEmpty()){
            throw new ExecutionStackEmpty("the stack is empty");
        }{
            Statement currentState = executionStack.pop();
            return currentState.evaluate(this);
        }
    }
    
    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }
    public ExecutionStack getExecutionStack() { return this.executionStack;}
    public SymbolTable getSymbolTable() { return this.symbolTable;}
    public OutputStream getOutput() { return this.output;}
    public FileTable getFileTable() { return this.fileTable;}
    public Heap getHeap() { return this.heap;}

    public String toString(){
        return "id: " + id + "\nexeStack: " + this.executionStack.toString() + "\nsymTable: " + this.symbolTable.toString() +
                "\noutput: " + this.output.toString() + "\nheap: " + this.heap.toString();
    }

    public String toFile() {
        return "id: " + id  + "\n" + executionStack.toFile() + '\n' + symbolTable.toFile() + '\n' + output.toFile() + '\n' + fileTable.toFile() +'\n' + heap.toFile() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~";

    }

    public void typeCheck() {
        Dict2<String, Type>typeEnv = new Dict2<>();
        this.getExecutionStack().peek().typeChecker(typeEnv);
    }
}
