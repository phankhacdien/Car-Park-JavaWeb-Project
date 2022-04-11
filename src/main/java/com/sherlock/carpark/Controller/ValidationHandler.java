package com.sherlock.carpark.Controller;

import com.sherlock.carpark.Exception.ApiException;
import com.sherlock.carpark.Exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = NotFoundException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handlerException(RuntimeException re) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(re.getMessage(), re, badRequest);
        return new ResponseEntity<>(apiException, badRequest);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        HttpStatus notFound = HttpStatus.NOT_FOUND;
//        List<ApiException> apiExceptionList = new ArrayList<>();
//        ex.getBindingResult()
//                .getAllErrors()
//                .forEach((error) -> {
//                    apiExceptionList.add(new ApiException(ex.getMessage(), ex, notFound));
//                });
//        return new ResponseEntity<>(apiExceptionList, notFound);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
