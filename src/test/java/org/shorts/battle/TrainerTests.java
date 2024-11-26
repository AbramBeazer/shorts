package org.shorts.battle;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.items.HeavyDutyBoots;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.PokemonTestUtils;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.items.Leftovers.LEFTOVERS;

class TrainerTests {

    Trainer trainer;
    Pokemon pokemon;

    @BeforeEach
    void setup() {
        pokemon = PokemonTestUtils.getDummyPokemon();
        pokemon.setMaxHP(100);
        pokemon.setCurrentHP(100);
        pokemon.setAbility(TORRENT);
        pokemon.setHeldItem(LEFTOVERS);
        trainer = new Trainer("Ash", List.of(pokemon));
    }

    @Test
    void testStealthRockNeutral() {
        trainer.addRocks();
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 8));
    }

    @Test
    void testStealthRockSuperEffective() {
        pokemon.setTypes(Set.of(Type.FIRE));
        trainer.addRocks();
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 4));
    }

    @Test
    void testSpikesOneLayer() {
        trainer.setSpikes(1);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 8));
    }

    @Test
    void testSpikesTwoLayers() {
        trainer.setSpikes(2);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 6));
    }

    @Test
    void testSpikesThreeLayers() {
        trainer.setSpikes(3);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 4));
    }

    @Test
    void testToxicSpikesOneLayer() {
        trainer.setToxicSpikes(1);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getStatus()).isEqualTo(Status.POISON);
    }

    @Test
    void testToxicSpikesTwoLayers() {
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getStatus().getType()).isEqualTo(StatusType.TOXIC_POISON);
    }

    @Test
    void testStickyWeb() {
        trainer.addStickyWeb();
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getStageSpeed()).isEqualTo(-1);
    }

    @Test
    void testAllHazards() {
        trainer.addStickyWeb();
        trainer.addRocks();
        trainer.setSpikes(3);
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getStageSpeed()).isEqualTo(-1);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() * 3 / 8));
        assertThat(pokemon.getStatus().getType()).isEqualTo(StatusType.TOXIC_POISON);
    }

    @Test
    void testStickyBoots() {
        pokemon.setHeldItem(HeavyDutyBoots.HEAVY_DUTY_BOOTS);
        trainer.addStickyWeb();
        trainer.addRocks();
        trainer.setSpikes(3);
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getStageSpeed()).isZero();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP());
        assertThat(pokemon.getStatus()).isEqualTo(Status.NONE);
        assertThat(trainer.getToxicSpikes()).isEqualTo(2);
    }

    @Test
    void testStickyBootsAbsorbToxicSpikes() {
        pokemon.setTypes(Set.of(Type.POISON));
        pokemon.setHeldItem(HeavyDutyBoots.HEAVY_DUTY_BOOTS);
        trainer.addStickyWeb();
        trainer.addRocks();
        trainer.setSpikes(3);
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards(pokemon);
        assertThat(pokemon.getStageSpeed()).isZero();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP());
        assertThat(pokemon.getStatus()).isEqualTo(Status.NONE);
        assertThat(trainer.getToxicSpikes()).isZero();
    }

    //TODO: Finish tests for Magic Guard, groundedness, and other cases
}
