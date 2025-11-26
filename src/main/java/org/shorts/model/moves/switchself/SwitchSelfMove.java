package org.shorts.model.moves.switchself;

import org.shorts.battle.Battle;
import org.shorts.model.moves.IMove;
import org.shorts.model.pokemon.Pokemon;

public interface SwitchSelfMove implements IMove {

    boolean canSwitchSelf(Pokemon user, Pokemon target, Battle battle);

    void switchSelf();
}
