package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

public class NullifyingAbility extends Ability {

    private NullifyingAbility(String name) {
        super(name);
    }

    public static final NullifyingAbility MOLD_BREAKER = new NullifyingAbility("Mold Breaker");
    public static final NullifyingAbility TERAVOLT = new NullifyingAbility("Teravolt");
    public static final NullifyingAbility TURBOBLAZE = new NullifyingAbility("Turboblaze");

    @Override
    public void beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        super.beforeAttack(self, opponent, battle, move);
        opponent.addVolatileStatus(VolatileStatus.ABILITY_IGNORED);
    } //TODO: Not sure if this needs to be beforeAttack or onMovePowerCalc. As early as possible for the purposes of ability ignoring.

    @Override
    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle) {
        super.afterAttack(self, opponent, battle);
        opponent.removeVolatileStatus(VolatileStatus.ABILITY_IGNORED);
    }
}
