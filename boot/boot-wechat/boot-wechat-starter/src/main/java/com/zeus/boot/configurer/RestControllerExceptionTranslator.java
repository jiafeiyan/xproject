package com.zeus.boot.configurer;

import com.alibaba.fastjson.JSONException;
import com.zeus.boot.commons.exception.BusinessException;
import com.zeus.boot.commons.exception.NotFoundException;
import com.zeus.boot.commons.message.ResponseMessage;
import com.zeus.boot.commons.validate.SimpleValidateResults;
import com.zeus.boot.commons.validate.ValidateResults;
import com.zeus.boot.commons.validate.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionTranslator {

    // logger
    private Logger logger = LoggerFactory.getLogger(RestControllerExceptionTranslator.class);

    @ExceptionHandler(JSONException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseMessage handleException(JSONException exception) {
        logger.error("json error", exception);
        return ResponseMessage.error(400, exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseMessage<List<ValidateResults.Result>> handleException(ValidationException exception) {
        return ResponseMessage.<List<ValidateResults.Result>>error(400, exception.getMessage())
                .result(exception.getResults());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseMessage handleException(BusinessException exception) {
        if (exception.getCause() != null) {
            logger.error("{}:{}", exception.getMessage(), exception.getStatus(), exception.getCause());
        }
        return ResponseMessage.error(exception.getStatus(), exception.getMessage()).result(exception.getCode());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ResponseMessage handleException(NotFoundException exception) {
        return ResponseMessage.error(404, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseMessage handleException(MethodArgumentNotValidException e) {
        SimpleValidateResults results = new SimpleValidateResults();
        e.getBindingResult().getAllErrors()
                .stream()
                .filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .forEach(fieldError -> results.addResult(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseMessage.error(400, results.getResults().size() == 0 ? e.getMessage() : results.getResults().get(0).getMessage()).result(results.getResults());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage handleException(RuntimeException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseMessage.error(500, exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage handleException(NullPointerException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseMessage.error(500, "服务器内部错误");
    }


    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseMessage handleException(SQLException exception) {
        logger.error(exception.getMessage(), exception);
        return ResponseMessage.error(500, "服务器内部错误");
    }

}
