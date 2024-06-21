package org.shorts.model.abilities.elementabsorb;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.ThunderFang;
import org.shorts.model.moves.multihit.MultiHitMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

class VoltAbsorbTests {

    private Pokemon user;
    private Pokemon other;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        move = new ThunderFang();
        user = PokemonTestUtils.getDummyPokemon();
        other = PokemonTestUtils.getDummyPokemon();
        battle = new DummySingleBattle(user, other);
    }

    @Test
    void testDoesNotActivateIfProtected() {
        final int halfHealth = user.getMaxHP() / 2;
        user.setCurrentHP(halfHealth);
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        move.execute(other, List.of(user), battle);
        assertThat(user.getCurrentHP()).isEqualTo(halfHealth);
    }

    @Test
    void testOnlyActivatedOnceByMultiHitMove() {
        final int halfHealth = user.getMaxHP() / 2;
        user.setCurrentHP(halfHealth);
        move = new MultiHitMove(
            "Test",
            10,
            100,
            Type.ELECTRIC,
            Move.Category.SPECIAL,
            Range.SINGLE_ADJACENT_ANY,
            24,
            false,
            0,
            2,
            2) {

        };
        move.execute(other, List.of(user), battle);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() * 3 / 4);
    }

    @Test
    void testAbsorbsElectricAttack() {
        assertThat(false).isTrue();
    }

    @Test
    void testAbsorbsElectricStatusMove() {
        assertThat(false).isTrue();
    }
}
