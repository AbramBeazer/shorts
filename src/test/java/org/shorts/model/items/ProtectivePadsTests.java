package org.shorts.model.items;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.abilities.ContactStatusAbility;
import org.shorts.model.abilities.DamageOnContactAbility;
import org.shorts.model.abilities.EffectSpore;
import org.shorts.model.abilities.UnseenFist;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.multihit.CometPunch;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

import static org.assertj.core.api.Assertions.*;
import static org.shorts.MockRandomReturnMax.*;
import static org.shorts.MockRandomReturnZero.*;
import static org.shorts.model.pokemon.PokemonTestUtils.*;

class ProtectivePadsTests {

    private Pokemon user;
    private Pokemon target;
    private Battle battle;
    private Move contactMove;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        target = getDummyPokemon();
        battle = new DummyBattle(user, target);
        contactMove = new Tackle();
        user.setHeldItem(ProtectivePads.PROTECTIVE_PADS);
        Main.HIT_RANDOM = ZERO_RANDOM;
        Main.CRIT_RANDOM = MAX_RANDOM;
        Main.DAMAGE_RANDOM = ZERO_RANDOM;
        Main.RANDOM = ZERO_RANDOM;
    }

    @Test
    void testUserProtectedFromDamageAfterContact() {
        target.setAbility(DamageOnContactAbility.IRON_BARBS);
        contactMove.execute(user, List.of(target), battle);
        assertThat(user.isAtFullHP()).isTrue();
    }

    @Test
    void testUserProtectedFromStatusOnContact() {
        target.setAbility(ContactStatusAbility.FLAME_BODY);
        contactMove.execute(user, List.of(target), battle);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testUserProtectedFromEffectSpore() {
        target.setAbility(EffectSpore.EFFECT_SPORE);
        contactMove.execute(user, List.of(target), battle);
        assertThat(user.getStatus()).isEqualTo(Status.NONE);
    }

    @Test
    void testDoesNotInterfereWithUnseenFist() {
        target.setAbility(DamageOnContactAbility.IRON_BARBS);
        user.setAbility(UnseenFist.UNSEEN_FIST);
        target.addVolatileStatus(new VolatileStatus(VolatileStatusType.PROTECTED, 1));
        contactMove.execute(user, List.of(target), battle);
        assertThat(user.isAtFullHP()).isTrue();
        assertThat(target.isAtFullHP()).isFalse();
    }
}
