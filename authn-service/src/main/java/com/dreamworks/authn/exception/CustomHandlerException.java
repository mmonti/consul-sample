package com.dreamworks.authn.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Throwables;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by mmonti on 3/30/16.
 */
@ControllerAdvice(annotations = RestController.class)
public class CustomHandlerException extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {FeignException.class, BadRequestException.class, HystrixRuntimeException.class})
    @ResponseBody
    public ApiError handleBadRequestException(final Exception exception, final WebRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), Throwables.getRootCause(exception).getMessage());
    }

}

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiError {

    private int code;
    private String description;

}