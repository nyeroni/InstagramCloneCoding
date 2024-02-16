package yerong.InstagramCloneCoding.handler.exception;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

    private String message;
    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap){
        super(message);
        this.message = message;
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }
}
