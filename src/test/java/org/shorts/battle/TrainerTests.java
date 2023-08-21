package org.shorts.battle;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shorts.model.Status;
import org.shorts.model.abilities.Ability;
import org.shorts.model.items.HeldItem;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;
import org.shorts.model.types.Type;

import static org.assertj.core.api.Assertions.assertThat;

public class TrainerTests {

    Trainer trainer;
    Pokemon pokemon;

    @BeforeEach
    void setup() {
        pokemon = new Squirtle();
        pokemon.setMaxHP(100);
        pokemon.setCurrentHP(100);
        pokemon.setAbility(new Ability("Torrent"));
        pokemon.setHeldItem(new HeldItem("Leftovers"));
        trainer = new Trainer("Ash", List.of(pokemon));
    }

    @Test
    void testStealthRockNeutral() {
        trainer.setRocks(true);
        trainer.applyEntryHazards();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 8));
    }

    @Test
    void testStealthRockSupereffective() {
        pokemon.setTypes(Set.of(Type.FIRE));
        trainer.setRocks(true);
        trainer.applyEntryHazards();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 4));
    }

    @Test
    void testSpikesOneLayer() {
        trainer.setSpikes(1);
        trainer.applyEntryHazards();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 8));
    }

    @Test
    void testSpikesTwoLayers() {
        trainer.setSpikes(2);
        trainer.applyEntryHazards();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 6));
    }

    @Test
    void testSpikesThreeLayers() {
        trainer.setSpikes(3);
        trainer.applyEntryHazards();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() / 4));
    }

    @Test
    void testToxicSpikesOneLayer() {
        trainer.setToxicSpikes(1);
        trainer.applyEntryHazards();
        assertThat(pokemon.getStatus()).isEqualTo(Status.POISON);
    }

    @Test
    void testToxicSpikesTwoLayers() {
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards();
        assertThat(pokemon.getStatus()).isEqualTo(Status.TOXIC_POISON);
    }

    @Test
    void testStickyWeb() {
        trainer.setStickyWeb(true);
        trainer.applyEntryHazards();
        assertThat(pokemon.getStageSpeed()).isEqualTo(-1);
    }

    @Test
    void testAllHazards() {
        trainer.setStickyWeb(true);
        trainer.setRocks(true);
        trainer.setSpikes(3);
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards();
        assertThat(pokemon.getStageSpeed()).isEqualTo(-1);
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP() - (pokemon.getMaxHP() * 3 / 8));
        assertThat(pokemon.getStatus()).isEqualTo(Status.TOXIC_POISON);
    }

    @Test
    void testStickyBoots() {
        pokemon.setHeldItem(new HeldItem("Heavy-Duty Boots"));
        trainer.setStickyWeb(true);
        trainer.setRocks(true);
        trainer.setSpikes(3);
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards();
        assertThat(pokemon.getStageSpeed()).isZero();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP());
        assertThat(pokemon.getStatus()).isEqualTo(Status.NONE);
        assertThat(trainer.getToxicSpikes()).isEqualTo(2);
    }

    @Test
    void testStickyBootsAbsorbToxicSpikes() {
        pokemon.setTypes(Set.of(Type.POISON));
        pokemon.setHeldItem(new HeldItem("Heavy-Duty Boots"));
        trainer.setStickyWeb(true);
        trainer.setRocks(true);
        trainer.setSpikes(3);
        trainer.setToxicSpikes(2);
        trainer.applyEntryHazards();
        assertThat(pokemon.getStageSpeed()).isZero();
        assertThat(pokemon.getCurrentHP()).isEqualTo(pokemon.getMaxHP());
        assertThat(pokemon.getStatus()).isEqualTo(Status.NONE);
        assertThat(trainer.getToxicSpikes()).isZero();
    }

    //TODO: Finish tests for Magic Guard, groundedness, and other cases
}
