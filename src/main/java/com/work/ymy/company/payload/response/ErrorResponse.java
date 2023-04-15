package com.work.ymy.company.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {
    private boolean success;
    private int httpStatusCode;
    //private List<Error> errors;
}
