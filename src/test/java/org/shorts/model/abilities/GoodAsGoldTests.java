package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Rest;
import org.shorts.model.moves.entryhazardsetter.StealthRock;
import org.shorts.model.moves.priority.HelpingHandMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class GoodAsGoldTests {

    private Pokemon attacker;
    private Pokemon gholdengo;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setUp() {
        attacker = getDummyPokemon();
        gholdengo = getDummyPokemon();
        battle = new DummyBattle(attacker, gholdengo);
    }

    @Test
    void testGhostCurseFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testDefogFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testLeechSeedFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testMementoFailsAndUserDoesNotFaint() {
        assertThat(false).isTrue();
    }

    @Test
    void testSpiteFailsAndPPIsNotReduced() {
        assertThat(false).isTrue();
    }

    @Test
    void testStrengthSapFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testTransformFails() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotBlockWhenTargetIsSelf() {
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotBlockMoveWhereTargetIsAll() {
        //        move = new Haze();
        assertThat(false).isTrue();
    }

    @Test
    void testDoesNotBlockMoveWhereTargetIsSide() {
        move = new StealthRock();
        move.execute(attacker, List.of(gholdengo), battle);
        assertThat(battle.getCorrespondingTrainer(gholdengo).hasRocks()).isTrue();
    }

    @Test
    void testBlocksHelpfulSingleTargetMoveFromAlly() {
        move = new HelpingHandMove();
        move.execute(attacker, List.of(gholdengo), battle);
        assertThat(gholdengo.hasVolatileStatus(VolatileStatusType.HELPING_HAND)).isFalse();
    }

    @Test
    void testBlocksHelpfulTargetAllAlliesMove() {
        //        move = new Howl();
        assertThat(false).isTrue();
    }
}
