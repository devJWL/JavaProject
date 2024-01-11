package exception;

public class NoDataException extends Exception{
    public NoDataException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
