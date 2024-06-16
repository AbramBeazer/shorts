package org.shorts.model.moves;

import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;

public interface SelfThawingMove {

    default void thawSelf(Pokemon user) {
        if (user.getStatus() == Status.FREEZE) {
            user.setStatus(Status.NONE);
        }
    }
}
