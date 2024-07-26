package org.shorts.model.moves.healthdependent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class HealthDependentMoveTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private HealthDependentMove move;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new HealthDependentMove(
            "Test",
            150,
            100,
            Type.NORMAL,
            Move.Category.SPECIAL,
            Range.ALL_ADJACENT_OPPONENTS,
            8,
            false,
            0) {

            @Override
            public double getPower(Pokemon user, Pokemon target, Battle battle) {
                return super.getPower(user, target, battle);
            }
        };
    }

    @Test
    void testPowerScalesWithUserHP() {
        user.setCurrentHP(user.getMaxHP());
        assertThat(move.getPower(user, target, battle)).isEqualTo(HealthDependentMove.MAX_POWER);
        user.setCurrentHP(user.getMaxHP() / 3);
        assertThat(move.getPower(user, target, battle)).isEqualTo(HealthDependentMove.MAX_POWER / 3d);
        //Minimum power is 1
        user.setCurrentHP(1);
        assertThat(move.getPower(user, target, battle)).isEqualTo(1);
    }

}
