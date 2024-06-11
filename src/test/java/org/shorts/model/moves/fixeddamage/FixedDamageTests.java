package org.shorts.model.moves.fixeddamage;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.Sex;
import org.shorts.model.pokemon.Axew;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Jigglypuff;
import org.shorts.model.pokemon.Misdreavus;
import org.shorts.model.pokemon.Pikachu;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.ContactStatusAbility.CUTE_CHARM;
import static org.shorts.model.abilities.ContactStatusAbility.STATIC_ABILITY;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.abilities.Rivalry.RIVALRY;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;

public class FixedDamageTests {

    private final Battle battle = new Battle(
        new Trainer("Red", List.of(new Squirtle(TORRENT))),
        new Trainer("Green", List.of(new Bulbasaur(OVERGROW))), 1);

    @Test
    void testSeismicToss() {
        SeismicToss seismicToss = new SeismicToss();
        Pokemon attacker = new Axew(RIVALRY);
        attacker.setLevel(100);
        Pokemon immuneDefender = new Misdreavus();
        Pokemon defender = new Pikachu(STATIC_ABILITY);
        attacker.setSex(Sex.FEMALE);
        immuneDefender.setSex(Sex.FEMALE);
        defender.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(seismicToss.calculateDamage(attacker, defender, battle)).isEqualTo(attacker.getLevel());
        assertThat(seismicToss.calculateDamage(attacker, immuneDefender, battle)).isZero();

        attacker.setAbility(SCRAPPY);
        assertThat(seismicToss.calculateDamage(attacker, immuneDefender, battle)).isEqualTo(attacker.getLevel());
    }

    @Test
    void testNightShade() {
        NightShade nightShade = new NightShade();
        Pokemon attacker = new Axew(RIVALRY);
        attacker.setLevel(100);
        Pokemon immuneDefender = new Jigglypuff(CUTE_CHARM);
        Pokemon defender = new Pikachu(STATIC_ABILITY);
        attacker.setSex(Sex.FEMALE);
        immuneDefender.setSex(Sex.FEMALE);
        defender.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(nightShade.calculateDamage(attacker, defender, battle)).isEqualTo(attacker.getLevel());
        assertThat(nightShade.calculateDamage(attacker, immuneDefender, battle)).isZero();
    }

    @Test
    void testDragonRage() {
        DragonRage dragonRage = new DragonRage();
        Pokemon attacker = new Axew(RIVALRY);
        Pokemon immuneDefender = new Jigglypuff(CUTE_CHARM);
        Pokemon defender = new Pikachu(STATIC_ABILITY);
        attacker.setSex(Sex.FEMALE);
        immuneDefender.setSex(Sex.FEMALE);
        defender.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(dragonRage.calculateDamage(attacker, defender, battle)).isEqualTo(DragonRage.FIXED_DAMAGE);
        assertThat(dragonRage.calculateDamage(attacker, immuneDefender, battle)).isZero();
    }

    @Test
    void testSonicBoom() {
        SonicBoom sonicBoom = new SonicBoom();
        Pokemon attacker = new Axew(RIVALRY);
        Pokemon immuneDefender = new Misdreavus();
        Pokemon defender = new Pikachu(STATIC_ABILITY);
        attacker.setSex(Sex.FEMALE);
        immuneDefender.setSex(Sex.FEMALE);
        defender.setSex(Sex.FEMALE); //This tests that the fixed damage is returned and abilities don't increase the damage

        assertThat(sonicBoom.calculateDamage(attacker, defender, battle)).isEqualTo(SonicBoom.FIXED_DAMAGE);
        assertThat(sonicBoom.calculateDamage(attacker, immuneDefender, battle)).isZero();
    }

}
