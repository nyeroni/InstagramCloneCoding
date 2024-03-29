package yerong.InstagramCloneCoding.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import yerong.InstagramCloneCoding.handler.exception.CustomApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationException;
import yerong.InstagramCloneCoding.util.Script;
import yerong.InstagramCloneCoding.web.dto.CMRespDto;

import java.util.Map;

@RestController
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    /**
     * CMRespDto vs Script
     * 1. 클라이언트에게 응답 : Script
     * 2. Ajax 통신 : CMRespDto
     * 3. Android 통신 : CMRespDto
     */
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        if(e.getErrorMap()==null){
            return Script.back(e.getMessage());
        }
        else return Script.back(e.getErrorMap().toString());
    }

    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e){
        return Script.back(e.getMessage());
    }
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<CMRespDto<?>> validationApiException(CustomValidationApiException e){
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e){
        return new ResponseEntity<CMRespDto<?>>(new CMRespDto(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}
