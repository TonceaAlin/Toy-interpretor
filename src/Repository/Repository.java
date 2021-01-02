package Repository;

import Model.ProgramState.ProgramState;

import java.util.List;

public interface Repository {
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> states);
    void addProgram(ProgramState programState);
    void logProgramStateExecution(ProgramState state) throws RuntimeException;
}
