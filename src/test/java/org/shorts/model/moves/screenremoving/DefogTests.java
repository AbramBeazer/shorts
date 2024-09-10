package org.shorts.model.moves.screenremoving;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Terrain;
import org.shorts.battle.Trainer;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.abilities.MagicBounce.MAGIC_BOUNCE;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class DefogTests {

    private Pokemon user;
    private Pokemon enemy;
    private Battle battle;
    private Defog defog;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        enemy = getDummyPokemon();
        battle = new DummyBattle(user, enemy);
        defog = new Defog();
        Main.HIT_RANDOM = ZERO_RANDOM;
    }

    @Test
    void testLowersTargetEvasion() {
        assertThat(enemy.getStageEvasion()).isZero();
        defog.execute(user, List.of(enemy), battle);
        assertThat(enemy.getStageEvasion()).isEqualTo(-1);
    }

    @Test
    void testIgnoreAccuracyCheck() {
        Main.HIT_RANDOM = MAX_RANDOM;
        assertThat(enemy.getStageEvasion()).isZero();
        defog.execute(user, List.of(enemy), battle);
        assertThat(enemy.getStageEvasion()).isEqualTo(-1);
    }

    @Test
    void testDoesNotLowerEvasionOfTargetBehindSubstitute() {
        enemy.addVolatileStatus(new SubstituteStatus(100));
        assertThat(enemy.getStageEvasion()).isZero();
        defog.execute(user, List.of(enemy), battle);
        assertThat(enemy.getStageEvasion()).isZero();
    }

    @Test
    void testDoesNotLowerEvasionOfSemiInvulnerableTarget() {
        enemy.addVolatileStatus(new VolatileStatus(VolatileStatusType.SEMI_INVULNERABLE, 1));
        assertThat(enemy.getStageEvasion()).isZero();
        defog.execute(user, List.of(enemy), battle);
        assertThat(enemy.getStageEvasion()).isZero();
    }

    @Test
    void testRemoveHazardsFromBothSides() {
        final Trainer player = battle.getCorrespondingTrainer(user);
        player.setToxicSpikes(1);
        player.setSpikes(1);
        player.addRocks();
        player.addStickyWeb();

        final Trainer opponent = battle.getCorrespondingTrainer(enemy);
        opponent.setToxicSpikes(2);
        opponent.setSpikes(3);
        opponent.addRocks();
        opponent.addStickyWeb();

        defog.execute(user, List.of(enemy), battle);

        assertThat(player.getToxicSpikes()).isZero();
        assertThat(player.getSpikes()).isZero();
        assertThat(player.hasRocks()).isFalse();
        assertThat(player.hasStickyWeb()).isFalse();

        assertThat(opponent.getToxicSpikes()).isZero();
        assertThat(opponent.getSpikes()).isZero();
        assertThat(opponent.hasRocks()).isFalse();
        assertThat(opponent.hasStickyWeb()).isFalse();
    }

    @Test
    void testClearsSafeguardMistAndScreensFromTargetSideOnly() {
        final Trainer player = battle.getCorrespondingTrainer(user);
        player.setLightScreenTurns(5);
        player.setReflectTurns(5);
        player.setAuroraVeilTurns(5);
        player.setMistTurns(5);
        player.setSafeguardTurns(5);

        final Trainer opponent = battle.getCorrespondingTrainer(enemy);
        opponent.setLightScreenTurns(5);
        opponent.setReflectTurns(5);
        opponent.setAuroraVeilTurns(5);
        opponent.setMistTurns(5);
        opponent.setSafeguardTurns(5);

        defog.execute(user, List.of(enemy), battle);

        assertThat(player.getLightScreenTurns()).isEqualTo(5);
        assertThat(player.getReflectTurns()).isEqualTo(5);
        assertThat(player.getAuroraVeilTurns()).isEqualTo(5);
        assertThat(player.getMistTurns()).isEqualTo(5);
        assertThat(player.getSafeguardTurns()).isEqualTo(5);

        assertThat(opponent.getLightScreenTurns()).isZero();
        assertThat(opponent.getReflectTurns()).isZero();
        assertThat(opponent.getAuroraVeilTurns()).isZero();
        assertThat(opponent.getMistTurns()).isZero();
        assertThat(opponent.getSafeguardTurns()).isZero();
    }

    @Test
    void testClearsTerrainFromField() {
        battle.setTerrain(Terrain.GRASSY, -1);
        defog.execute(user, List.of(enemy), battle);
        assertThat(battle.getTerrain()).isEqualTo(Terrain.NONE);
    }

    @Test
    void testReflectedByMagicBounce() {
        enemy.setAbility(MAGIC_BOUNCE);

        final Trainer player = battle.getCorrespondingTrainer(user);
        player.setLightScreenTurns(5);
        player.setReflectTurns(5);
        player.setAuroraVeilTurns(5);
        player.setMistTurns(5);
        player.setSafeguardTurns(5);

        final Trainer opponent = battle.getCorrespondingTrainer(enemy);
        opponent.setLightScreenTurns(5);
        opponent.setReflectTurns(5);
        opponent.setAuroraVeilTurns(5);
        opponent.setMistTurns(5);
        opponent.setSafeguardTurns(5);

        defog.execute(user, List.of(enemy), battle);

        assertThat(player.getLightScreenTurns()).isZero();
        assertThat(player.getReflectTurns()).isZero();
        assertThat(player.getAuroraVeilTurns()).isZero();
        assertThat(player.getMistTurns()).isZero();
        assertThat(player.getSafeguardTurns()).isZero();

        assertThat(opponent.getLightScreenTurns()).isEqualTo(5);
        assertThat(opponent.getReflectTurns()).isEqualTo(5);
        assertThat(opponent.getAuroraVeilTurns()).isEqualTo(5);
        assertThat(opponent.getMistTurns()).isEqualTo(5);
        assertThat(opponent.getSafeguardTurns()).isEqualTo(5);
    }
}
