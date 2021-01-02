package Repository;

import Model.ProgramState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repo implements Repository{
    private  List<ProgramState> memory;
    private final String logFilePath;

    public Repo(String logFilePath) {
        this.memory = new ArrayList<>();
        this.logFilePath = logFilePath;
    }


    @Override
    public List<ProgramState> getProgramList() {
        return memory;
    }

    @Override
    public void setProgramList(List<ProgramState> states) {
        memory = states;
    }

    @Override
    public void addProgram(ProgramState programState) {
        memory.add(programState);
    }


    @Override
    public void logProgramStateExecution(ProgramState state) throws RuntimeException {
        PrintWriter logFile = null;
        try{
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (logFile != null) {
            logFile.println(state.toFile());
        }
        assert logFile != null;
        logFile.close();

    }
}
