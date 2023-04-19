package com.work.ymy.company.payload.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SuccessResponse {
    private boolean success;
    private HttpStatus httpStatusCode;
    private String message;
    private Object data;
}
