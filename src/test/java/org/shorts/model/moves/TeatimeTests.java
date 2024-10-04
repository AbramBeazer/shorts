package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.berries.OranBerry.ORAN_BERRY;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class TeatimeTests {

    private Teatime move;
    private Pokemon user;
    private Pokemon teammate;
    private Pokemon enemy1;
    private Pokemon enemy2;
    private Battle battle;

    @BeforeEach
    void setup() {
        move = new Teatime();
        user = getDummyPokemon();
        teammate = getDummyPokemon();
        enemy1 = getDummyPokemon();
        enemy2 = getDummyPokemon();
        user.setHeldItem(ORAN_BERRY);
        user.takeDamage(10);
        teammate.setHeldItem(ORAN_BERRY);
        teammate.takeDamage(10);
        enemy1.setHeldItem(ORAN_BERRY);
        enemy1.takeDamage(10);
        enemy2.setHeldItem(ORAN_BERRY);
        enemy2.takeDamage(10);

        battle = new DummyBattle(List.of(user, teammate), List.of(enemy1, enemy2), 2);
    }

    @Test
    void testAllPokemonEatBerries() {
        move.execute(user, battle.getPokemonWithinRange(user, move.getRange(user)), battle);
        assertThat(user.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(teammate.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy1.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(enemy2.getHeldItem()).isEqualTo(NO_ITEM);
        assertThat(user.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(teammate.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy1.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(enemy2.getConsumedItem()).isEqualTo(ORAN_BERRY);
        assertThat(user.isAtFullHP()).isTrue();
        assertThat(teammate.isAtFullHP()).isTrue();
        assertThat(enemy1.isAtFullHP()).isTrue();
        assertThat(enemy2.isAtFullHP()).isTrue();
    }

    @Test
    void testBerryIsEatenEvenAboveThreshold() {
        assertThat(false).isTrue();
    }

    @Test
    void testBypassesSubstituteButNotSemiInvulnerable() {
        assertThat(false).isTrue();
    }

    @Test
    void testIgnoresUnnerve() {
        assertThat(false).isTrue();
    }

    @Test
    void testIgnoresMagicRoom() {
        assertThat(false).isTrue();
    }

    @Test
    void testElectrifiedTeatimeShouldBeAbsorbedByAbilityEvenWithNoBerry() {
        assertThat(false).isTrue();
    }
}
