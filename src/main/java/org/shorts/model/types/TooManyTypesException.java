package org.shorts.model.types;

import java.util.Set;
import java.util.stream.Collectors;

public class TooManyTypesException extends IllegalArgumentException {

    static final String TOO_MANY_TYPES_ERROR_MESSAGE = "No Pokemon can have more than two types! Provided: ";

    public TooManyTypesException(Set<Type> types) {
        super(TOO_MANY_TYPES_ERROR_MESSAGE + types.stream().map(Type::toString).collect(Collectors.joining(", ")));
    }
}
