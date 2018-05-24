package com.zeus.boot.commons.validate;


import com.zeus.boot.commons.exception.BusinessException;
import lombok.Getter;

import java.util.List;

public class ValidationException extends BusinessException {

    private static final long serialVersionUID = 1L;

    @Getter
    private ValidateResults results;

    public ValidationException(String message) {
        super(message, 400);
    }

    public ValidationException(String message, String field) {
        super(message, 400);
        results = new SimpleValidateResults().addResult(field, message);
    }

    public ValidationException(ValidateResults results) {
        super(results.toString(), 400);
        this.results = results;
    }

    public List<ValidateResults.Result> getResults() {
        if (results == null) {
            return null;
        }
        return results.getResults();
    }
}
