package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Turn;
import org.shorts.model.moves.CloseCombat;
import org.shorts.model.moves.Crunch;
import org.shorts.model.moves.Growl;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class DefiantTests {

    private Pokemon user;
    private Pokemon opponent;
    private Battle battle;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        user.setAbility(Defiant.DEFIANT);
        opponent = getDummyPokemon();
        battle = new DummyBattle(user, opponent);
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testAttackRaisesTwoStagesAfterDropFromOpponent() {
        assertThat(user.getStageAttack()).isZero();

        new Turn(opponent, new Growl(), 0).takeTurn(battle);
        assertThat(user.getStageAttack()).isOne();

        new Turn(opponent, new Crunch(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageAttack()).isEqualTo(3);
    }

    @Test
    void testAttackNotRaisedIfDropsOwnStats() {
        assertThat(user.getStageAttack()).isZero();
        assertThat(user.getStageDefense()).isZero();
        assertThat(user.getStageSpecialDefense()).isZero();

        new Turn(user, new CloseCombat(), 0).takeTurn(battle);
        assertThat(user.getStageDefense()).isEqualTo(-1);
        assertThat(user.getStageSpecialDefense()).isEqualTo(-1);
        assertThat(user.getStageAttack()).isEqualTo(2);
    }
}
