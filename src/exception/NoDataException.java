package exception;

public class NoDataException extends Exception{
    public NoDataException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error : 존재하지 않는 "+super.getMessage()+"입니다. 다시 입력해주세요.";
    }
}
