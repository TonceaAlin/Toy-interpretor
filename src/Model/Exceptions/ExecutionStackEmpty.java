package Model.Exceptions;

public class ExecutionStackEmpty extends RuntimeException{
    public ExecutionStackEmpty(String errorMessage){
        super(errorMessage);
    }
}
