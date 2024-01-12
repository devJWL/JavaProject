package exception;

public class ExistDataException extends Exception{
    public ExistDataException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error : "+super.getMessage()+"는 이미 등록되어 있습니다. 다시 입력해주세요.";
    }
}
