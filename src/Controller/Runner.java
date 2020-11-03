package Controller;

import Model.Exceptions.ExecutionStackEmpty;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.ProgramState;
import Model.Statem.Statement;

public class Runner {
    public Runner(){

    }
    public ProgramState oneStep(ProgramState state) throws RuntimeException{
        ExecutionStack stack = state.getExecutionStack();
        if(stack.isEmpty()){
            throw new ExecutionStackEmpty("the stack is empty");
        }{
            Statement currentState = stack.pop();
            return currentState.evaluate(state);
        }
    }
    public void allSteps(ProgramState state){
        System.out.println("execute " + state.toString());
        while(!state.getExecutionStack().isEmpty()){
            oneStep(state);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");

    }
}
