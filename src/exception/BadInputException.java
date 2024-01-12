package exception;

import java.util.Objects;

public class BadInputException extends Exception{
    public BadInputException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        if(Objects.equals(super.getMessage(), "round range")){
            return "회차는 1~10회차 사이입니다. 다시 입력해주세요.";
        }
        else if(Objects.equals(super.getMessage(), "score range")){
            return "점수는  1~100점 사이입니다. 다시 입력해주세요.";
        }
        return "잘못된 input입니다";
    }
}
