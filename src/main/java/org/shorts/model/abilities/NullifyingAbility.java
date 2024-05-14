package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

public class NullifyingAbility extends Ability {

    private NullifyingAbility(String name) {
        super(name);
    }

    public static final NullifyingAbility MOLD_BREAKER = new NullifyingAbility("Mold Breaker");
    public static final NullifyingAbility TERAVOLT = new NullifyingAbility("Teravolt");
    public static final NullifyingAbility TURBOBLAZE = new NullifyingAbility("Turboblaze");

    @Override
    public double beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        super.beforeAttack(self, opponent, battle, move);
        opponent.addVolatileStatus(VolatileStatus.ABILITY_IGNORED);
        return 1;
    } //TODO: Not sure if this needs to be beforeAttack or onMovePowerCalc. As early as possible for the purposes of ability ignoring.

    @Override
    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle) {
        super.afterAttack(self, opponent, battle);
        opponent.removeVolatileStatus(VolatileStatusType.ABILITY_IGNORED);
    }
}
