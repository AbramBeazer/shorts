package org.shorts.battle;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.pokemon.Axew;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Charmander;
import org.shorts.model.pokemon.Gyarados;
import org.shorts.model.pokemon.Medicham;
import org.shorts.model.pokemon.Pikachu;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Scizor;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.AttackDoublingAbility.PURE_POWER;
import static org.shorts.model.abilities.ContactStatusAbility.STATIC_ABILITY;
import static org.shorts.model.abilities.Intimidate.INTIMIDATE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.BLAZE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.SWARM;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.abilities.Rivalry.RIVALRY;
import static org.shorts.model.pokemon.PokemonTestUtils.getDummyPokemon;

class BattleTests {

    private Battle battle;
    private Trainer playerOne;
    private Trainer playerTwo;

    @BeforeEach
    void setup() {
        playerOne = new Trainer(
            "Red",
            List.of(
                new Bulbasaur(OVERGROW),
                new Charmander(BLAZE),
                new Squirtle(TORRENT),
                new Pikachu(STATIC_ABILITY)));
        playerTwo = new Trainer(
            "Green",
            List.of(new Axew(RIVALRY), new Gyarados(INTIMIDATE), new Medicham(PURE_POWER), new Scizor(SWARM)));
        battle = new Battle(playerOne, playerTwo, 1);
    }

    @Test
    void testPrintFieldSingleBattle() {
        final Pokemon user = getDummyPokemon();
        final Pokemon target = getDummyPokemon();
        final Battle battle = new DummySingleBattle(user, target);

        battle.printField(battle.getPlayerOne());
    }

}
