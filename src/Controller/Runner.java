package Controller;

import Model.Exceptions.ExecutionStackEmpty;
import Model.ProgramState.ExecutionStack;
import Model.ProgramState.ProgramState;
import Model.Statem.Statement;
import Model.Values.Ref;
import Model.Values.Value;
import Repository.Repository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    private final Repository repository;
    private ExecutorService executor;
    public Runner(Repository repository){

        this.repository = repository;
    }
    public List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList){
        return inProgramList.stream()
                .filter(p->p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> states) throws InterruptedException {
        states.forEach(
                p->{
                    try{
                        repository.logProgramStateExecution(p);
                    }catch (RuntimeException exception){
                        exception.printStackTrace();
                    }
                }
        );
        List<Callable<ProgramState>> callList = states.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());

        List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                .map(future-> {
                    try {
                    return future.get();}
                catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;}
                )
                .filter(p->p!=null)
                .collect(Collectors.toList());
        states.addAll(newProgramsList);
        states.forEach(
                p-> {
                        try{
                            repository.logProgramStateExecution(p);

                        }catch (RuntimeException exception){
                            exception.getStackTrace();
                        }
                });
        repository.setProgramList(states);
    }
    public void allSteps() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedProgram(repository.getProgramList());
        while(programStates.size() > 0){
            //conservativeGarbage
            conservativeGarbageCollector(programStates);
            oneStepForAllPrograms(programStates);
            programStates = removeCompletedProgram(repository.getProgramList());
        }
        executor.shutdownNow();
        repository.setProgramList(programStates);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        

    }

    private void conservativeGarbageCollector(List<ProgramState> programStates) {
        var heap = Objects.requireNonNull(programStates.stream()
                                .map(p->getAddrFromSymbolTable(
                                        p.getSymbolTable().getContent().values(),
                                        p.getHeap().getContent().values()
                                ))
                                .map(Collection::stream)
                                .reduce(Stream::concat).orElse(null).collect(Collectors.toList()));
        programStates.forEach(programState -> {
            programState.getHeap().setContent(
                    unsafeGarbageCollector( heap,
                            programStates.get(0).getHeap().getContent()
                            )
            );
        });

    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symbolTableAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e->symbolTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    Set<Integer> getAddrFromSymbolTable(Collection<Value> symbolTableValues, Collection<Value> heap){
        return Stream.concat(symbolTableValues.stream(), heap.stream())
                .filter(v-> v instanceof Ref)
                .map(v-> {Ref v1 = (Ref)v;return v1.getAddress();})
                .collect(Collectors.toSet());
    }

    public void typeCheck() {
        repository.getProgramList().get(0).typeCheck();
    }
}
