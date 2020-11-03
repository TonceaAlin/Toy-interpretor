package Model.ProgramState;

import Model.ADTs.Stack2;
import Model.Statem.Statement;

public class ExecutionStack {
    private final Stack2<Statement> executionStack;

    public ExecutionStack(Statement state)
    {
        this.executionStack = new Stack2<Statement>();
        this.executionStack.push(state);

    }

    public ExecutionStack(){
        this.executionStack = new Stack2<Statement>();
    }

    public void push(Statement statement){
        this.executionStack.push(statement);
    }
    public Statement pop(){
        return this.executionStack.pop();
    }
    public boolean isEmpty(){return this.executionStack.size()==0;}

    public String toString(){
        String result = " ";
        for(var each: this.executionStack.getAll()){
            result += each.toString();
        }
        return result;
    }
}
