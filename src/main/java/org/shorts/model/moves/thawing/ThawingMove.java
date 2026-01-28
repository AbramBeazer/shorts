package org.shorts.model.moves.thawing;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.IMove;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public interface ThawingMove extends IMove {

    @Override
    default boolean isThawingMove() {
        return true;
    }
}
