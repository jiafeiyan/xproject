package com.zeus.boot.commons.validate;

import java.io.Serializable;
import java.util.List;

public interface ValidateResults extends Serializable {

    boolean isSuccess();

    List<Result> getResults();

    interface Result extends Serializable {
        String getField();

        String getMessage();
    }

}
