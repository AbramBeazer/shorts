package org.shorts.model.status;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;

public interface AbstractStatusType {

    boolean isStatusPossible(Pokemon target, Battle battle);
}
