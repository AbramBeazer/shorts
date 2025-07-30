package org.shorts.model.moves.floating;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class WishTests {

    @Test
    void testUsesHalfOfUsersHPAndNotReceivers() {
        Pokemon user = getDummyPokemon();
        user.setMaxHP(100);
        user.setCurrentHP(1);
        Pokemon receiver = getDummyPokemon();
        receiver.setMaxHP(75);
        receiver.setCurrentHP(1);
        Battle battle = new DummyBattle(user, getDummyPokemon());

        new Wish().executeOnTarget(user, receiver, battle);

        assertThat(user.getCurrentHP()).isOne();
        assertThat(receiver.getCurrentHP()).isEqualTo(1 + user.getMaxHP() / 2);
    }

    @Test
    void testFailsIfUserIsHealBlocked() {
        Pokemon user = getDummyPokemon();
        user.addVolatileStatus(new VolatileStatus(VolatileStatusType.HEAL_BLOCKED, 500));
        user.setMaxHP(100);
        user.setCurrentHP(1);
        Pokemon receiver = getDummyPokemon();
        receiver.setMaxHP(75);
        receiver.setCurrentHP(1);
        Battle battle = new DummyBattle(user, getDummyPokemon());

        assertThat(battle.getFloatingEffects()).isEmpty();
        new Wish().execute(user, List.of(user), battle);
        assertThat(battle.getFloatingEffects()).isEmpty();
    }

    @Test
    void testFailsIfReceiverIsHealBlocked() {
        Pokemon user = getDummyPokemon();
        user.setMaxHP(100);
        user.setCurrentHP(1);
        Pokemon receiver = getDummyPokemon();
        receiver.setMaxHP(75);
        receiver.setCurrentHP(1);
        receiver.addVolatileStatus(new VolatileStatus(VolatileStatusType.HEAL_BLOCKED, 500));
        Battle battle = new DummyBattle(user, getDummyPokemon());

        new Wish().executeOnTarget(user, receiver, battle);
        assertThat(receiver.getCurrentHP()).isOne();
    }

    @Test
    void testFailsIfReceiverHasFainted() {
        Pokemon user = getDummyPokemon();
        Pokemon receiver = getDummyPokemon();
        receiver.setCurrentHP(0);
        receiver.setStatus(Status.FAINTED);
        Battle battle = new DummyBattle(user, getDummyPokemon());

        new Wish().executeOnTarget(user, receiver, battle);
        assertThat(receiver.getCurrentHP()).isZero();
        assertThat(receiver.getStatus()).isEqualTo(Status.FAINTED);
    }
}
