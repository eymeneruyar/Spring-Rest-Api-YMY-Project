package com.work.ymy.company.config;

import com.work.ymy.company.payload.response.ErrorResponse;
import com.work.ymy.company.prop.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidArgument(MethodArgumentNotValidException exception) {
        Error error = new Error();
        List<Error> errorList = new ArrayList<>();
        ErrorResponse errorResponse = new ErrorResponse();
        exception.getBindingResult().getFieldErrors().forEach(e -> {
            error.setField(e.getField());
            error.setMessage(e.getDefaultMessage());
            errorList.add(error);
        });
        errorResponse.setErrors(errorList);
        errorResponse.setSuccess(false);
        errorResponse.setMessage("Giriş parametrelerini kontrol ediniz!");
        errorResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST);
        return errorResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(RuntimeException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setSuccess(false);
        errorResponse.setMessage("Beklenmeyen bir hata oluştu: " + exception.getMessage());
        return errorResponse;
    }

    //TODO: Toptal sitesinde ki örneğe göre tekrar düzenlenecek.


}
