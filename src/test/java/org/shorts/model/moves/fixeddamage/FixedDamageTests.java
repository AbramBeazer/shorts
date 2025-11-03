package org.shorts.model.moves.fixeddamage;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.Sex;
import org.shorts.model.moves.FreezeDry;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Axew;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Jigglypuff;
import org.shorts.model.pokemon.Misdreavus;
import org.shorts.model.pokemon.Pikachu;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.abilities.ContactStatusAbility.CUTE_CHARM;
import static org.shorts.model.abilities.ContactStatusAbility.STATIC_ABILITY;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.abilities.Rivalry.RIVALRY;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class FixedDamageTests {

    private Battle battle;
    private Pokemon user;
    private Pokemon target;

    @BeforeEach
    void setup() {
        Main.RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
    }

    @Test
    void testSeismicToss() {
        final SeismicToss move = new SeismicToss();
        user.setAbility(RIVALRY);
        user.setSex(Sex.FEMALE);
        target.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(move.calculateDamage(user, target, battle)).isEqualTo(user.getLevel());
        target.setTypes(Set.of(Type.GHOST));
        assertThat(move.calculateDamage(user, target, battle)).isZero();
        user.setAbility(SCRAPPY);
        assertThat(move.calculateDamage(user, target, battle)).isEqualTo(user.getLevel());
    }

    @Test
    void testNightShade() {
        final NightShade move = new NightShade();
        user.setAbility(RIVALRY);
        user.setSex(Sex.FEMALE);
        target.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(move.calculateDamage(user, target, battle)).isZero();
        target.setTypes(Set.of(Type.FAIRY));
        assertThat(move.calculateDamage(user, target, battle)).isEqualTo(user.getLevel());
    }

    @Test
    void testDragonRage() {
        final DragonRage move = new DragonRage();
        user.setAbility(RIVALRY);
        user.setSex(Sex.FEMALE);
        target.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(move.calculateDamage(user, target, battle)).isEqualTo(DragonRage.FIXED_DAMAGE);
        target.setTypes(Set.of(Type.FAIRY));
        assertThat(move.calculateDamage(user, target, battle)).isZero();
    }

    @Test
    void testSonicBoom() {
        final SonicBoom move = new SonicBoom();
        user.setAbility(RIVALRY);
        user.setSex(Sex.FEMALE);
        target.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(move.calculateDamage(user, target, battle)).isEqualTo(SonicBoom.FIXED_DAMAGE);
        target.setTypes(Set.of(Type.GHOST));
        assertThat(move.calculateDamage(user, target, battle)).isZero();
        user.setAbility(SCRAPPY);
        assertThat(move.calculateDamage(user, target, battle)).isEqualTo(SonicBoom.FIXED_DAMAGE);
    }

}
