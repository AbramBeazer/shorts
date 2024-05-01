package org.shorts.battle;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.shorts.model.pokemon.Abomasnow;
import org.shorts.model.pokemon.Golduck;
import org.shorts.model.pokemon.Groudon;
import org.shorts.model.pokemon.Hippowdon;
import org.shorts.model.pokemon.Kyogre;
import org.shorts.model.pokemon.MegaRayquaza;
import org.shorts.model.pokemon.PrimalGroudon;
import org.shorts.model.pokemon.PrimalKyogre;
import org.shorts.model.pokemon.Rayquaza;
import org.shorts.model.pokemon.Tyranitar;

import static org.shorts.model.abilities.weather.NegatingWeatherAbility.CLOUD_NINE;
import static org.shorts.model.abilities.weather.WeatherAbility.SAND_STREAM;
import static org.shorts.model.abilities.weather.WeatherAbility.SNOW_WARNING;

class WeatherTerrainIntegrationTests {

    private SingleBattle battle;

    @BeforeEach
    void setup() {
        battle = new SingleBattle(
            new Trainer(
                "Red",
                List.of(
                    new Kyogre(),
                    new Abomasnow(SNOW_WARNING),
                    new Golduck(CLOUD_NINE),
                    new Hippowdon(SAND_STREAM),
                    new PrimalGroudon(),
                    new PrimalKyogre())),
            new Trainer(
                "Green",
                List.of(new Groudon(), new Tyranitar(SAND_STREAM), new Rayquaza(), new MegaRayquaza())));
    }
}
