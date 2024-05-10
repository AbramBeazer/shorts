package org.shorts.battle;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.shorts.model.pokemon.Axew;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Charmander;
import org.shorts.model.pokemon.Gyarados;
import org.shorts.model.pokemon.Medicham;
import org.shorts.model.pokemon.Pikachu;
import org.shorts.model.pokemon.Scizor;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.AttackDoublingAbility.PURE_POWER;
import static org.shorts.model.abilities.ContactStatusAbility.STATIC;
import static org.shorts.model.abilities.Intimidate.INTIMIDATE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.BLAZE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.SWARM;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.abilities.Rivalry.RIVALRY;

class SingleBattleTests {

    private SingleBattle battle;
    private Trainer playerOne;
    private Trainer playerTwo;

    @BeforeEach
    void setup() {
        playerOne = new Trainer(
            "Red",
            List.of(new Bulbasaur(OVERGROW), new Charmander(BLAZE), new Squirtle(TORRENT), new Pikachu(STATIC)));
        playerTwo = new Trainer(
            "Green",
            List.of(new Axew(RIVALRY), new Gyarados(INTIMIDATE), new Medicham(PURE_POWER), new Scizor(SWARM)));
        battle = new SingleBattle(playerOne, playerTwo);
    }
}
