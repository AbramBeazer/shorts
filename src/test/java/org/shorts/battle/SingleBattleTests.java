package org.shorts.battle;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.shorts.model.pokemon.Axew;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Charmander;
import org.shorts.model.pokemon.Drifloon;
import org.shorts.model.pokemon.Gyarados;
import org.shorts.model.pokemon.Jigglypuff;
import org.shorts.model.pokemon.Medicham;
import org.shorts.model.pokemon.Onix;
import org.shorts.model.pokemon.Pikachu;
import org.shorts.model.pokemon.Scizor;
import org.shorts.model.pokemon.Sneasel;
import org.shorts.model.pokemon.Squirtle;

class SingleBattleTests {

    private SingleBattle battle;
    private Trainer playerOne;
    private Trainer playerTwo;

    @BeforeEach
    void setup() {
        playerOne = new Trainer(
            "Red",
            List.of(new Bulbasaur(), new Charmander(), new Squirtle(), new Pikachu(), new Jigglypuff(), new Onix()));
        playerTwo = new Trainer(
            "Green",
            List.of(new Axew(), new Gyarados(), new Medicham(), new Scizor(), new Drifloon(), new Sneasel()));
        battle = new SingleBattle(playerOne, playerTwo);
    }
}
