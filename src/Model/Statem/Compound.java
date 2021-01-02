package Model.Statem;

import Model.ADTs.Dict2;
import Model.Exceptions.TypeException;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.ProgramState;
import Model.Types.Type;

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

        return null;
    }

    @Override
    public Dict2<String, Type> typeChecker(Dict2<String, Type> typeEnv) throws TypeException {
        return right.typeChecker(left.typeChecker(typeEnv));
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
