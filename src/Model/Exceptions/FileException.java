package Model.Exceptions;

public class FileException extends RuntimeException {
    public FileException(String errorMessage){
        super(errorMessage);
    }
}
