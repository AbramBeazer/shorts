package org.shorts.model.status;

import java.util.Set;

import org.shorts.model.types.Type;

public class TypeChangeStatus extends VolatileStatus {

    private Set<Type> originalTypes;

    public TypeChangeStatus(VolatileStatusType type, int turnsRemaining, Set<Type> originalTypes) {
        super(type, turnsRemaining);
        this.originalTypes = originalTypes;
    }

    public Set<Type> getOriginalTypes() {
        return originalTypes;
    }
}
