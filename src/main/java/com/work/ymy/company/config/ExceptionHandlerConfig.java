package com.work.ymy.company.config;

import com.work.ymy.company.payload.response.ErrorResponse;
import com.work.ymy.company.prop.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidArgument(MethodArgumentNotValidException ex) {
        Error error = new Error();
        List<Error> errorList = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            error.setField(e.getField());
            error.setMessage(e.getDefaultMessage());
            errorList.add(error);
        });
        errorResponse.setErrors(errorList);
        errorResponse.setSuccess(false);
        errorResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        return errorResponse;
    }

}
