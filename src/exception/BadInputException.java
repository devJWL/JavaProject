package exception;

public class BadInputException extends Exception{
    public BadInputException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
