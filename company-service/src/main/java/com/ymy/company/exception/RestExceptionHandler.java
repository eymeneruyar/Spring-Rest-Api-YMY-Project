package com.ymy.company.exception;

import com.ymy.company.apierror.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(RestExceptionHandler.class);

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     *
     * @param exception the MethodArgumentNotValidException that is thrown when @Valid validation fails
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleInvalidArgument(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("Validation error");
        apiError.addValidationErrors(exception.getBindingResult().getFieldErrors());
        apiError.addValidationError(exception.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<?> handleRuntimeException(RuntimeException exception){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage("Beklenmeyen bir hata oluştu.");
        apiError.setDebugMessage(exception.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle MissingServletRequestParameterException. Triggered when a 'required' request parameter is missing.
     *
     * @param exception MissingServletRequestParameterException
     * @return the ApiError object
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException exception) {
        String error = exception.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, exception));
    }

    /**
     * Handle HttpMediaTypeNotSupportedException. This one triggers when JSON is invalid as well.
     *
     * @param exception HttpMediaTypeNotSupportedException
     * @return the ApiError object
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception) {
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        exception.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), exception));
    }

    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param exception the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
        apiError.setMessage(exception.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     *
     * @param exception HttpMessageNotReadableException
     * @param request WebRequest
     * @return the ApiError object
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        LOGGER.info("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, exception));
    }

    /**
     * Handle HttpMessageNotWritableException.
     *
     * @param exception HttpMessageNotWritableException
     * @param headers HttpHeaders
     * @param status  HttpStatus
     * @param request WebRequest
     * @return the ApiError object
     */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Error writing JSON output";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, exception));
    }

    /**
     * Handle NoHandlerFoundException.
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("Could not find the %s method for URL %s", exception.getHttpMethod(), exception.getRequestURL()));
        apiError.setDebugMessage(exception.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle DataIntegrityViolationException, inspects the cause for different DB causes.
     *
     * @param exception the DataIntegrityViolationException
     * @return the ApiError object
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        if (exception.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", exception.getCause()));
        }
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception));
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param exception the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'", exception.getName(), exception.getValue(), exception.getRequiredType().getSimpleName()));
        apiError.setDebugMessage(exception.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handle Exception, handle generic Exception.class
     *
     * @param exception the Exception
     * @return the ApiError object
     */
    @ExceptionHandler(CompanyNotFoundException.class)
    protected ResponseEntity<?> handleCompanyNotFoundException(CompanyNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        apiError.setMessage(exception.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
