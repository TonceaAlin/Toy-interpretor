package Repository;

import Model.ProgramState.ProgramState;

import java.util.ArrayList;

public class Repo implements Repository{
    ArrayList<ProgramState> memory;

    @Override
    public ProgramState getCertainProgram(ProgramState state) {
        return memory.get(memory.indexOf(state));
    }
}
