package com.ymy.company.payload.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SuccessResponse {
    private boolean success;
    private HttpStatus httpStatusCode;
    private String message;
    private Object data;
}
