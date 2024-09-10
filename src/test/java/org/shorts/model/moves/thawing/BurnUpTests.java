package org.shorts.model.moves.thawing;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.MockRandomReturnMax.MAX_RANDOM;
import static org.shorts.MockRandomReturnZero.ZERO_RANDOM;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.Status.FREEZE;
import static org.shorts.model.status.Status.NONE;

class BurnUpTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private BurnUp move;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        Set<Type> userTypes = new HashSet<>();
        userTypes.add(Type.FIRE);
        user.setTypes(userTypes);
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        Main.RANDOM = ZERO_RANDOM;
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        move = new BurnUp();
    }

    @Test
    void testThawsFrozenTarget() {
        target.setStatus(Status.FREEZE);
        move.execute(user, List.of(target), battle);
        assertThat(target.getStatus()).isEqualTo(NONE);
    }

    @Test
    void testThawsFrozenUser() {
        user.setStatus(Status.FREEZE);
        move.execute(user, List.of(target), battle);
        assertThat(user.getStatus()).isEqualTo(NONE);
    }

    @Test
    void testUserLosesFireTypeAfterAttack() {
        move.execute(user, List.of(target), battle);
        assertThat(user.getTypes()).doesNotContain(Type.FIRE);
        assertThat(user.getTypes()).isEmpty();
    }

    @Test
    void testUserLosesFireTypeAfterAttackButRetainsSecondaryType() {
        Set<Type> userTypes = new HashSet<>();
        userTypes.add(Type.FIRE);
        userTypes.add(Type.FLYING);
        user.setTypes(userTypes);

        move.execute(user, List.of(target), battle);
        assertThat(user.getTypes()).doesNotContain(Type.FIRE).contains(Type.FLYING);
    }

    @Test
    void testFailsToThawUserIfUserIsNotFireType() {
        user.setStatus(Status.FREEZE);
        user.setTypes(Set.of(Type.WATER));
        move.execute(user, List.of(target), battle);
        assertThat(user.getStatus()).isEqualTo(FREEZE);
    }

}
