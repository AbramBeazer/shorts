package org.shorts.model.types;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypesTests {

    @Test
    void testStab() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.NORMAL);
        assertThat(Type.getSTABMultiplier(Type.NORMAL, defenderTypes)).isEqualTo(Type.STAB);
    }

    @Test
    void testSuperEffective() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getTypeMultiplier(Type.ROCK, defenderTypes)).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testQuadEffective() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getTypeMultiplier(Type.FIGHTING, defenderTypes)).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testStabSuperEffective() {
        Set<Type> attackerTypes = Set.of(Type.FIRE, Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getSTABMultiplier(Type.FIRE, attackerTypes) * Type.getTypeMultiplier(
            Type.FIRE,
            defenderTypes)).isEqualTo(
            Type.STAB * Type.SUPER_EFFECTIVE);
    }

    @Test
    void testStabQuadEffective() {
        Set<Type> attackerTypes = Set.of(Type.FIRE, Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getSTABMultiplier(Type.FIGHTING, attackerTypes) * Type.getTypeMultiplier(
            Type.FIGHTING,
            defenderTypes)).isEqualTo(
            Type.STAB * Type.QUAD_EFFECTIVE);
    }

    @Test
    void testNotVeryEffective() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getTypeMultiplier(Type.FAIRY, defenderTypes)).isEqualTo(Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testQuadResist() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getTypeMultiplier(Type.FIGHTING, defenderTypes)).isEqualTo(Type.QUAD_RESIST);
    }

    @Test
    void testStabNotVeryEffective() {
        Set<Type> attackerTypes = Set.of(Type.POISON);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getSTABMultiplier(Type.POISON, attackerTypes) * Type.getTypeMultiplier(
            Type.POISON,
            defenderTypes)).isEqualTo(
            Type.STAB * Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testStabQuadResist() {
        Set<Type> attackerTypes = Set.of(Type.FIGHTING, Type.STEEL);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getSTABMultiplier(Type.FIGHTING, attackerTypes) * Type.getTypeMultiplier(
            Type.FIGHTING,
            defenderTypes)).isEqualTo(
            Type.STAB * Type.QUAD_RESIST);
    }

    @Test
    void testImmune() {
        Set<Type> defenderTypes = Set.of(Type.NORMAL, Type.GHOST);
        assertThat(Type.getTypeMultiplier(Type.NORMAL, defenderTypes)).isEqualTo(Type.IMMUNE);
        assertThat(Type.getTypeMultiplier(Type.GHOST, defenderTypes)).isEqualTo(Type.IMMUNE);
    }

    @Test
    void testTooManyAttackerTypes() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL, Type.ICE, Type.DARK);
        Set<Type> defenderTypes = Set.of(Type.FIRE);
        var ex = assertThrows(Exception.class, () -> Type.getSTABMultiplier(Type.ICE, defenderTypes));
        assertThat(ex.getMessage()).isEqualTo(TooManyTypesException.TOO_MANY_TYPES_ERROR_MESSAGE);
    }

    @Test
    void testTooManyDefenderTypes() {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.FIRE, Type.FIGHTING, Type.GHOST);
        var ex = assertThrows(Exception.class, () -> Type.getTypeMultiplier(Type.NORMAL, defenderTypes));
        assertThat(ex.getMessage()).isEqualTo(TooManyTypesException.TOO_MANY_TYPES_ERROR_MESSAGE);
    }
}
