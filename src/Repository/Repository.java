package Repository;

import Model.ProgramState.ProgramState;

public interface Repository {
    ProgramState getCertainProgram(ProgramState state);
}
