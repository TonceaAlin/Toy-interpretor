package Model.Statem;

import Model.ProgramState.ProgramState;

public class Nop implements Statement{
    public Nop(){

    }
    @Override
    public ProgramState evaluate(ProgramState state) throws RuntimeException {

        return state;
    }
}
