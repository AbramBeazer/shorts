package org.shorts.model.types;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypesTests {

    private Pokemon attacker;

    @BeforeEach
    void setup() {
        attacker = PokemonTestUtils.getDummyPokemon();
    }

    @Test
    void testStab() {
        attacker.setTypes(Set.of(Type.NORMAL));
        assertThat(Type.getSTABMultiplier(Type.NORMAL, attacker)).isEqualTo(Type.STAB);
    }

    @Test
    void testTera() {
        attacker.setTypes(Set.of(Type.NORMAL));
        attacker.setTera(true);
        attacker.setTeraType(Type.FIRE);
        assertThat(Type.getSTABMultiplier(Type.FIRE, attacker)).isEqualTo(Type.STAB);
    }

    @Test
    void testStabPlusTera() {
        attacker.setTypes(Set.of(Type.NORMAL));
        attacker.setTera(true);
        attacker.setTeraType(Type.NORMAL);
        assertThat(Type.getSTABMultiplier(Type.NORMAL, attacker)).isEqualTo(Type.TERA_STAB);
    }

    @Test
    void testSuperEffective() {
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getTypeMultiplier(Type.ROCK, defenderTypes)).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testQuadEffective() {
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getTypeMultiplier(Type.FIGHTING, defenderTypes)).isEqualTo(Type.EXTREMELY_EFFECTIVE);
    }

    @Test
    void testStabSuperEffective() {
        attacker.setTypes(Set.of(Type.FIRE, Type.FIGHTING));
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(
            Type.getSTABMultiplier(Type.FIRE, attacker) * Type.getTypeMultiplier(Type.FIRE, defenderTypes))
            .isEqualTo(Type.STAB * Type.SUPER_EFFECTIVE);
    }

    @Test
    void testTeraSuperEffective() {
        attacker.setTypes(Set.of(Type.FIRE, Type.FIGHTING));
        attacker.setTera(true);
        attacker.setTeraType(Type.BUG);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(
            Type.getSTABMultiplier(Type.BUG, attacker) * Type.getTypeMultiplier(Type.BUG, defenderTypes))
            .isEqualTo(Type.STAB * Type.SUPER_EFFECTIVE);
    }

    @Test
    void testStabPlusTeraSuperEffective() {
        attacker.setTypes(Set.of(Type.FIRE, Type.FIGHTING));
        attacker.setTera(true);
        attacker.setTeraType(Type.FIRE);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(
            Type.getSTABMultiplier(Type.FIRE, attacker) * Type.getTypeMultiplier(Type.FIRE, defenderTypes))
            .isEqualTo(Type.TERA_STAB * Type.SUPER_EFFECTIVE);
    }

    @Test
    void testStabQuadEffective() {
        attacker.setTypes(Set.of(Type.FIRE, Type.FIGHTING));
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(
            Type.getSTABMultiplier(Type.FIGHTING, attacker) * Type.getTypeMultiplier(Type.FIGHTING, defenderTypes))
            .isEqualTo(Type.STAB * Type.EXTREMELY_EFFECTIVE);
    }

    @Test
    void testTeraQuadEffective() {
        attacker.setTypes(Set.of(Type.NORMAL));
        attacker.setTera(true);
        attacker.setTeraType(Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(
            Type.getSTABMultiplier(Type.FIGHTING, attacker) * Type.getTypeMultiplier(Type.FIGHTING, defenderTypes))
            .isEqualTo(Type.STAB * Type.EXTREMELY_EFFECTIVE);
    }

    @Test
    void testStabPlusTeraQuadEffective() {
        attacker.setTypes(Set.of(Type.FIRE, Type.FIGHTING));
        attacker.setTera(true);
        attacker.setTeraType(Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(
            Type.getSTABMultiplier(Type.FIGHTING, attacker) * Type.getTypeMultiplier(Type.FIGHTING, defenderTypes))
            .isEqualTo(Type.TERA_STAB * Type.EXTREMELY_EFFECTIVE);
    }

    @Test
    void testNotVeryEffective() {
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getTypeMultiplier(Type.FAIRY, defenderTypes)).isEqualTo(Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testStabNotVeryEffective() {
        attacker.setTypes(Set.of(Type.POISON));
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getSTABMultiplier(Type.POISON, attacker) * Type.getTypeMultiplier(Type.POISON, defenderTypes))
            .isEqualTo(Type.STAB * Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testTeraNotVeryEffective() {
        attacker.setTypes(Set.of(Type.NORMAL));
        attacker.setTera(true);
        attacker.setTeraType(Type.POISON);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(
            Type.getSTABMultiplier(Type.POISON, attacker) * Type.getTypeMultiplier(Type.POISON, defenderTypes))
            .isEqualTo(Type.STAB * Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testStabPlusTeraNotVeryEffective() {
        attacker.setTypes(Set.of(Type.POISON));
        attacker.setTera(true);
        attacker.setTeraType(Type.POISON);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getSTABMultiplier(Type.POISON, attacker) * Type.getTypeMultiplier(Type.POISON, defenderTypes))
            .isEqualTo(Type.TERA_STAB * Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testQuadResist() {
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getTypeMultiplier(Type.FIGHTING, defenderTypes)).isEqualTo(Type.QUAD_RESIST);
    }

    @Test
    void testStabQuadResist() {
        attacker.setTypes(Set.of(Type.FIGHTING));
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(
            Type.getSTABMultiplier(Type.FIGHTING, attacker) * Type.getTypeMultiplier(Type.FIGHTING, defenderTypes))
            .isEqualTo(Type.STAB * Type.QUAD_RESIST);
    }

    @Test
    void testTeraQuadResist() {
        attacker.setTypes(Set.of(Type.NORMAL));
        attacker.setTera(true);
        attacker.setTeraType(Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(
            Type.getSTABMultiplier(Type.FIGHTING, attacker) * Type.getTypeMultiplier(Type.FIGHTING, defenderTypes))
            .isEqualTo(Type.STAB * Type.QUAD_RESIST);
    }

    @Test
    void testStabPlusTeraQuadResist() {
        attacker.setTypes(Set.of(Type.FIGHTING));
        attacker.setTera(true);
        attacker.setTeraType(Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(
            Type.getSTABMultiplier(Type.FIGHTING, attacker) * Type.getTypeMultiplier(Type.FIGHTING, defenderTypes))
            .isEqualTo(Type.TERA_STAB * Type.QUAD_RESIST);
    }

    @Test
    void testImmune() {
        Set<Type> defenderTypes = Set.of(Type.NORMAL, Type.GHOST);
        assertThat(Type.getTypeMultiplier(Type.NORMAL, defenderTypes)).isEqualTo(Type.IMMUNE);
        assertThat(Type.getTypeMultiplier(Type.GHOST, defenderTypes)).isEqualTo(Type.IMMUNE);
    }

    @Test
    void testTooManyAttackerTypes() {
        attacker.setTypes(Set.of(Type.NORMAL, Type.ICE, Type.DARK));

        var ex = assertThrows(Exception.class, () -> Type.getSTABMultiplier(Type.ICE, attacker));
        assertThat(ex.getMessage()).contains(TooManyTypesException.TOO_MANY_TYPES_ERROR_MESSAGE);
        for (Type type : attacker.getTypes()) {
            assertThat(ex.getMessage()).contains(type.toString());
        }
    }

    @Test
    void testTooManyDefenderTypes() {
        Set<Type> defenderTypes = Set.of(Type.FIRE, Type.FIGHTING, Type.GHOST);

        var ex = assertThrows(Exception.class, () -> Type.getTypeMultiplier(Type.NORMAL, defenderTypes));
        assertThat(ex.getMessage()).contains(TooManyTypesException.TOO_MANY_TYPES_ERROR_MESSAGE);
        for (Type type : defenderTypes) {
            assertThat(ex.getMessage()).contains(type.toString());
        }
    }
}
