package yerong.InstagramCloneCoding.handler.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationApiException;
import yerong.InstagramCloneCoding.handler.exception.CustomValidationException;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class ValidationAdvice {

    @Around("execution(* yerong.InstagramCloneCoding.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("===web api controller===");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if(arg instanceof BindingResult){
                log.info("유효성 검사를 하는 함수입니다.");
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();

                    for(FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성 검사 실패", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }

    @Around("execution(* yerong.InstagramCloneCoding.web.controller.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("===web controller===");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if(arg instanceof BindingResult){
                log.info("유효성 검사를 하는 함수입니다.");
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()){
                    Map<String, String> errorMap = new HashMap<>();

                    for(FieldError error : bindingResult.getFieldErrors()){
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패", errorMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}

