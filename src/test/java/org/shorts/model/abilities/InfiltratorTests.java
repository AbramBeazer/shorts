package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Crunch;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Surf;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.ThunderWave;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.StatusType.PARALYZE;

class InfiltratorTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        user.setAbility(Infiltrator.INFILTRATOR);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        trainer = battle.getOpposingTrainer(user);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testBypassesReflectWithPhysicalMove() {
        final Move move = new Tackle();
        move.execute(user, List.of(target), battle);
        final int damageNoScreens = target.getMaxHP() - target.getCurrentHP();
        target.maxPotion();

        trainer.setReflectTurns(4);
        move.execute(user, List.of(target), battle);
        assertThat(damageNoScreens).isEqualTo(target.getMaxHP() - target.getCurrentHP());
    }

    @Test
    void testBypassesLightScreenWithSpecialMove() {
        final Move move = new Surf();
        move.execute(user, List.of(target), battle);
        final int damageNoScreens = target.getMaxHP() - target.getCurrentHP();
        target.maxPotion();

        trainer.setLightScreenTurns(4);
        move.execute(user, List.of(target), battle);
        assertThat(damageNoScreens).isEqualTo(target.getMaxHP() - target.getCurrentHP());
    }

    @Test
    void testBypassesAuroraVeilWithPhysicalMove() {
        final Move move = new Tackle();
        move.execute(user, List.of(target), battle);
        final int damageNoScreens = target.getMaxHP() - target.getCurrentHP();
        target.maxPotion();

        trainer.setAuroraVeilTurns(4);
        move.execute(user, List.of(target), battle);
        assertThat(damageNoScreens).isEqualTo(target.getMaxHP() - target.getCurrentHP());
    }

    @Test
    void testBypassesAuroraVeilWithSpecialMove() {
        final Move move = new Surf();
        move.execute(user, List.of(target), battle);
        final int damageNoScreens = target.getMaxHP() - target.getCurrentHP();
        target.maxPotion();

        trainer.setAuroraVeilTurns(4);
        move.execute(user, List.of(target), battle);
        assertThat(damageNoScreens).isEqualTo(target.getMaxHP() - target.getCurrentHP());
    }

    @Test
    void testIgnoresSafeguard() {
        final Move move = new ThunderWave();
        trainer.setSafeguardTurns(5);
        move.execute(user, List.of(target), battle);
        assertThat(user.getStatus().getType()).isNotEqualTo(PARALYZE);
    }

    @Test
    void testIgnoresMist() {
        assertThat(target.getStageDefense()).isZero();
        final Move move = new Crunch();
        trainer.setMistTurns(5);
        move.execute(user, List.of(target), battle);
        assertThat(target.getStageDefense()).isEqualTo(-1);
    }

    @Test
    void testIgnoresSubstitute() {
        assertThat(target.isAtFullHP()).isTrue();
        final Move move = new Tackle();
        final int subHP = target.getMaxHP() / 4;
        target.addVolatileStatus(new SubstituteStatus(subHP));
        move.execute(user, List.of(target), battle);
        assertThat(target.hasVolatileStatus(VolatileStatusType.SUBSTITUTE)).isTrue();
        assertThat(((SubstituteStatus) target.getVolatileStatus(VolatileStatusType.SUBSTITUTE)).getSubHP()).isEqualTo(
            subHP);
        assertThat(target.getCurrentHP()).isLessThan(target.getMaxHP());
    }

    @Test
    void testDoesNotIgnoreSubstituteIfUsingTransform() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotIgnoreSubstituteIfUsingSkyDrop() {
        assertThat(false).isTrue();
    }

}
