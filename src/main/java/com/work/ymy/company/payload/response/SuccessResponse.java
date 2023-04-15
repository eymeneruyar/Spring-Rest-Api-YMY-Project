package com.work.ymy.company.payload.response;

import lombok.Data;

@Data
public class SuccessResponse {
    private boolean success;
    private int httpStatusCode;
    private String message;
    private Object data;
}
