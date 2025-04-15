package org.shorts.model.moves.screenremoving;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.types.Type.GHOST;

class ScreenRemovingMoveTests {

    private Pokemon user;
    private ScreenRemovingMove move;
    private Battle battle;
    private Pokemon target;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        move = new ScreenRemovingMove(
            "Test",
            50,
            100,
            Type.NORMAL,
            Move.Category.PHYSICAL,
            Range.NORMAL,
            10,
            true,
            0) {

        };
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
    }

    @Test
    void testRemovesScreenAndDealsRegularDamage() {
        final Trainer targetTrainer = battle.getCorrespondingTrainer(target);

        move.execute(user, List.of(target), battle);
        int damageWithNoScreens = target.getMaxHP() - target.getCurrentHP();
        target.maxPotion();

        targetTrainer.setAuroraVeilTurns(5);
        targetTrainer.setReflectTurns(5);
        targetTrainer.setLightScreenTurns(5);

        move.execute(user, List.of(target), battle);
        assertThat(targetTrainer.getAuroraVeilTurns()).isZero();
        assertThat(targetTrainer.getReflectTurns()).isZero();
        assertThat(targetTrainer.getLightScreenTurns()).isZero();
        assertThat(target.getMaxHP() - target.getCurrentHP()).isEqualTo(damageWithNoScreens);
    }

    @Test
    void testFailsToRemoveScreenIfTargetIsImmune() {
        target.setTypes(Set.of(GHOST));
        final Trainer targetTrainer = battle.getCorrespondingTrainer(target);

        targetTrainer.setAuroraVeilTurns(5);
        targetTrainer.setReflectTurns(5);
        targetTrainer.setLightScreenTurns(5);

        move.execute(user, List.of(target), battle);
        assertThat(targetTrainer.getAuroraVeilTurns()).isEqualTo(5);
        assertThat(targetTrainer.getReflectTurns()).isEqualTo(5);
        assertThat(targetTrainer.getLightScreenTurns()).isEqualTo(5);
        assertThat(target.getCurrentHP()).isEqualTo(target.getMaxHP());
    }

    @Test
    void testRemovesScreenIfTargetIsAlly() {
        battle = new DummyBattle(List.of(user, target), List.of(getDummyPokemon()), 2);
        final Trainer targetTrainer = battle.getCorrespondingTrainer(target);

        move.execute(user, List.of(target), battle);
        int damageWithNoScreens = target.getMaxHP() - target.getCurrentHP();
        target.maxPotion();

        targetTrainer.setAuroraVeilTurns(5);
        targetTrainer.setReflectTurns(5);
        targetTrainer.setLightScreenTurns(5);

        move.execute(user, List.of(target), battle);
        assertThat(battle.getCorrespondingTrainer(user)).isEqualTo(targetTrainer);
        assertThat(targetTrainer.getAuroraVeilTurns()).isZero();
        assertThat(targetTrainer.getReflectTurns()).isZero();
        assertThat(targetTrainer.getLightScreenTurns()).isZero();
        assertThat(target.getMaxHP() - target.getCurrentHP()).isEqualTo(damageWithNoScreens);
    }
}
