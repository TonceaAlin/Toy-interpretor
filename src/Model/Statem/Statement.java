package Model.Statem;

import Model.ProgramState.ProgramState;

public interface Statement {
        ProgramState evaluate(ProgramState state) throws RuntimeException;
}
