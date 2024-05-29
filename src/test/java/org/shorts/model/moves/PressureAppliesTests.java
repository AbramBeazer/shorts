package org.shorts.model.moves;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummySingleBattle;
import org.shorts.model.moves.entryhazardsetter.StealthRock;
import org.shorts.model.moves.entryhazardsetter.StickyWeb;
import org.shorts.model.pokemon.Groudon;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Pressure.PRESSURE;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.types.Type.GHOST;

class PressureAppliesTests {

    private Pokemon attacker = new Groudon();
    private Pokemon defender = getDummyPokemon();
    private final Battle battle = new DummySingleBattle();

    @BeforeEach
    void setup() {
        this.defender.setAbility(PRESSURE);
    }

    @Test
    void testReturnsTrueForPhysicalAttack() {
        Move move = new Earthquake();
        assertThat(move.pressureApplies(attacker, defender)).isTrue();
    }

    @Test
    void testReturnsTrueForSpecialAttack() {
        Move move = new Scald();
        assertThat(move.pressureApplies(attacker, defender)).isTrue();
    }

    @Test
    void testReturnsTrueForStatusMoveThatTargetsOthers() {
        Move move = new StealthRock();
        assertThat(move.pressureApplies(attacker, defender)).isTrue();
    }

    @Test
    void testReturnsFalseForStatusMoveThatOnlyTargetsSelf() {
        Move move = new Rest();
        move.determineTargetAndExecuteMove(attacker, defender, battle);
        assertThat(move.getCurrentPP()).isEqualTo(move.getMaxPP() - 1);
    }

    @Test
    void testReturnsFalseForStickyWeb() {
        Move move = new StickyWeb();
        assertThat(move.pressureApplies(attacker, defender)).isFalse();
    }

    @Test
    void testCurse() {
        Curse curse = new Curse();
        assertThat(attacker.getTypes()).doesNotContain(GHOST);
        curse.determineTargetAndExecuteMove(attacker, defender, battle);
        assertThat(curse.getCurrentPP()).isEqualTo(curse.getMaxPP() - 1);

        curse = new Curse();
        attacker.setTypes(Set.of(GHOST));
        curse.determineTargetAndExecuteMove(attacker, defender, battle);
        assertThat(curse.getCurrentPP()).isEqualTo(curse.getMaxPP() - 2);
    }
}
