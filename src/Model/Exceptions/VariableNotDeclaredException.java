package Model.Exceptions;

public class VariableNotDeclaredException extends RuntimeException{
    public VariableNotDeclaredException(String errorMessage){
        super(errorMessage);
    }
}
