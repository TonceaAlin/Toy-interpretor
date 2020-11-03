package Model.Exceptions;

public class DivisionByZero extends RuntimeException {
    public DivisionByZero(String errorMessage){
        super(errorMessage);
    }
}
