package org.shorts.model.abilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.battle.Battle;
import org.shorts.battle.DummyBattle;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;
import static org.shorts.model.status.VolatileStatusType.HEAL_BLOCKED;

class CheekPouchTests {

    private Pokemon user;
    private Pokemon opponent;
    private final Berry berry = new Berry("test", 100) {

        @Override
        public boolean canDoEffect(Pokemon user) {
            return true;
        }

        @Override
        public void doEffect(Pokemon user) {

        }
    };
    private Battle battle;

    @BeforeEach
    void setUp() {
        user = getDummyPokemon();
        user.setCurrentHP(user.getMaxHP() / 3);
        user.setAbility(CheekPouch.CHEEK_POUCH);
        opponent = getDummyPokemon();
        battle = new DummyBattle(user, opponent);
    }

    @Test
    void testCheekPouchHealsWhenEatingBerry() {
        berry.tryEatingOwnBerry(user, battle);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() * 2 / 3);
        berry.tryEatingOtherBerry(user);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP());
    }

    @Test
    void testHealBlockPreventsEatingCheekPouch() {
        user.addVolatileStatus(new VolatileStatus(HEAL_BLOCKED, 1));
        berry.tryEatingOwnBerry(user, battle);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() / 3);
        berry.tryEatingOtherBerry(user);
        assertThat(user.getCurrentHP()).isEqualTo(user.getMaxHP() / 3);
    }
}
