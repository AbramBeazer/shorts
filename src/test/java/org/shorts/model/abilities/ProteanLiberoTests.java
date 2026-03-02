package org.shorts.model.abilities;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Toxic;
import org.shorts.model.moves.thawing.FlameWheel;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class ProteanLiberoTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testUserChangesTypeBeforeMoveExecutesAndGetsSTAB() {
        final Move move = new FlameWheel();
        final Set<Type> expectedType = Set.of(move.getType());

        user.addVolatileStatus(VolatileStatus.ABILITY_SUPPRESSED);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getTypes()).isNotEqualTo(expectedType);
        final double baseDamage = target.getMaxHP() - target.getCurrentHP();
        assertThat(baseDamage).isPositive();

        target.fullRestore();

        user.removeVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED);
        new Turn(user, move, 0).takeTurn(battle);
        assertThat(user.getTypes()).isEqualTo(expectedType);
        final double stabDamage = target.getMaxHP() - target.getCurrentHP();
        assertThat(stabDamage).isEqualTo(baseDamage * Type.STAB);
    }

    @Test
    void testUserChangesTypeAndHitsWithToxic() {
        user.setStageAccuracy(-6);
        target.setStageEvasion(6);
        Main.HIT_RANDOM = MAX_RANDOM;
        new Turn(user, new Toxic(), 0).takeTurn(battle);
        assertThat(target.getStatus().getType()).isEqualTo(StatusType.TOXIC_POISON);
    }

    @Test
    void testDoesNotActivateIfMoveFails() {
        assertThat(false).isTrue();
    }
}
