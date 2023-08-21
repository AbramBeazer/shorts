package org.shorts.model.types;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypesTests {

    @Test
    void testStab() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.NORMAL);
        assertThat(Type.getMultiplier(attackerTypes, Type.NORMAL, defenderTypes)).isEqualTo(Type.STAB);
    }

    @Test
    void testSuperEffective() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getMultiplier(attackerTypes, Type.ROCK, defenderTypes)).isEqualTo(Type.SUPER_EFFECTIVE);
    }

    @Test
    void testQuadEffective() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getMultiplier(attackerTypes, Type.FIGHTING, defenderTypes)).isEqualTo(Type.QUAD_EFFECTIVE);
    }

    @Test
    void testStabSuperEffective() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.FIRE, Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getMultiplier(attackerTypes, Type.FIRE, defenderTypes)).isEqualTo(
            Type.STAB * Type.SUPER_EFFECTIVE);
    }

    @Test
    void testStabQuadEffective() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.FIRE, Type.FIGHTING);
        Set<Type> defenderTypes = Set.of(Type.ICE, Type.DARK);
        assertThat(Type.getMultiplier(attackerTypes, Type.FIGHTING, defenderTypes)).isEqualTo(
            Type.STAB * Type.QUAD_EFFECTIVE);
    }

    @Test
    void testNotVeryEffective() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getMultiplier(attackerTypes, Type.FAIRY, defenderTypes)).isEqualTo(Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testQuadResist() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getMultiplier(attackerTypes, Type.FIGHTING, defenderTypes)).isEqualTo(Type.QUAD_RESIST);
    }

    @Test
    void testStabNotVeryEffective() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.POISON);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getMultiplier(attackerTypes, Type.POISON, defenderTypes)).isEqualTo(
            Type.STAB * Type.NOT_VERY_EFFECTIVE);
    }

    @Test
    void testStabQuadResist() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.FIGHTING, Type.STEEL);
        Set<Type> defenderTypes = Set.of(Type.POISON, Type.FLYING);
        assertThat(Type.getMultiplier(attackerTypes, Type.FIGHTING, defenderTypes)).isEqualTo(
            Type.STAB * Type.QUAD_RESIST);
    }

    @Test
    void testImmune() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL, Type.GHOST);
        Set<Type> defenderTypes = Set.of(Type.NORMAL, Type.GHOST);
        assertThat(Type.getMultiplier(attackerTypes, Type.NORMAL, defenderTypes)).isEqualTo(Type.IMMUNE);
        assertThat(Type.getMultiplier(attackerTypes, Type.GHOST, defenderTypes)).isEqualTo(Type.IMMUNE);
    }

    @Test
    void testTooManyAttackerTypes() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL, Type.ICE, Type.DARK);
        Set<Type> defenderTypes = Set.of(Type.FIRE);
        var ex = assertThrows(Exception.class, () -> Type.getMultiplier(attackerTypes, Type.ICE, defenderTypes));
        assertThat(ex.getMessage()).isEqualTo(TooManyTypesException.TOO_MANY_TYPES_ERROR_MESSAGE);
    }

    @Test
    void testTooManyDefenderTypes() throws Exception {
        Set<Type> attackerTypes = Set.of(Type.NORMAL);
        Set<Type> defenderTypes = Set.of(Type.FIRE, Type.FIGHTING, Type.GHOST);
        var ex = assertThrows(Exception.class, () -> Type.getMultiplier(attackerTypes, Type.NORMAL, defenderTypes));
        assertThat(ex.getMessage()).isEqualTo(TooManyTypesException.TOO_MANY_TYPES_ERROR_MESSAGE);
    }
}
