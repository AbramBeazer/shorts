package org.shorts.model.types;

public class TooManyTypesException extends IllegalArgumentException {

    static final String TOO_MANY_TYPES_ERROR_MESSAGE = "No Pokemon can have more than two types";

    @Override
    public String getMessage() {
        return TOO_MANY_TYPES_ERROR_MESSAGE;
    }
}
