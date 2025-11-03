package org.shorts.model.moves;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.Comatose.COMATOSE;
import static org.shorts.model.abilities.MagicBounce.MAGIC_BOUNCE;
import static org.shorts.model.abilities.NullifyingAbility.MOLD_BREAKER;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;

class GastroAcidTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Move move;

    @BeforeEach
    void setup() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        move = new GastroAcid();
        battle = new DummyBattle();
    }

    @Test
    void testSuppressesMostAbilities() {
        target.setAbility(TORRENT);
        move.execute(user, List.of(target), battle);
        assertThat(target.hasVolatileStatus(ABILITY_SUPPRESSED)).isTrue();
    }

    @Test
    void testDoesNotSuppressUnsuppressableAbilities() {
        target.setAbility(COMATOSE);
        move.execute(user, List.of(target), battle);
        assertThat(target.hasVolatileStatus(ABILITY_SUPPRESSED)).isFalse();
    }

    @Test
    void testReflectedByMagicBounce() {
        target.setAbility(MAGIC_BOUNCE);
        move.execute(user, List.of(target), battle);
        assertThat(target.hasVolatileStatus(ABILITY_SUPPRESSED)).isFalse();
        assertThat(user.hasVolatileStatus(ABILITY_SUPPRESSED)).isTrue();
    }

    @Test
    void testReflectedByMagicCoat() {
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.MAGIC_COAT, 1));
        move.execute(user, List.of(target), battle);
        assertThat(target.hasVolatileStatus(ABILITY_SUPPRESSED)).isFalse();
        assertThat(user.hasVolatileStatus(ABILITY_SUPPRESSED)).isTrue();
    }

    @Test
    void testNotReflectedByMagicBounceWhenUserHasMoldBreaker() {
        user.setAbility(MOLD_BREAKER);
        target.setAbility(MAGIC_BOUNCE);
        move.execute(user, List.of(target), battle);
        assertThat(target.hasVolatileStatus(ABILITY_SUPPRESSED)).isTrue();
        assertThat(user.hasVolatileStatus(ABILITY_SUPPRESSED)).isFalse();
    }

}
