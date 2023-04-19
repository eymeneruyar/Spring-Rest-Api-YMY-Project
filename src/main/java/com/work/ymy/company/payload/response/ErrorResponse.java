package com.work.ymy.company.payload.response;

import com.work.ymy.company.prop.Error;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {
    private boolean success;
    private String message;
    private HttpStatus httpStatusCode;
    private List<Error> errors;
}
