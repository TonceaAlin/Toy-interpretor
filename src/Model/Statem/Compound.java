package Model.Statem;

import Model.ProgramState.ExecutionStack;
import Model.ProgramState.ProgramState;

public class Compound implements Statement{

    private final Statement left;
    private final Statement right;

    public Compound(Statement left, Statement right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {
        ExecutionStack stack = state.getExecutionStack();
        stack.push(right);
        stack.push(left);

        return state;
    }

    public Statement getRight() {
        return right;
    }

    public Statement getLeft() {
        return left;
    }

    public String toString(){
        return this.left.toString() + ", " + this.right.toString();
    }
}
