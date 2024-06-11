package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.Nature;
import org.shorts.model.PokedexEntry;

import static org.shorts.model.abilities.DummyAbility.DUMMY_ABILITY;

public final class PokemonTestUtils {

    public static final PokedexEntry DUMMY_DEX;
    public static final int[] dummyEVs = new int[] { 0, 0, 0, 0, 0, 0 };

    static {
        DUMMY_DEX = new PokedexEntry();
        DUMMY_DEX.setSpeciesName("TEST MON");
        DUMMY_DEX.setHeight(1);
        DUMMY_DEX.setWeight(1);
        DUMMY_DEX.setBaseHP(300);
        DUMMY_DEX.setBaseAtk(100);
        DUMMY_DEX.setBaseDef(100);
        DUMMY_DEX.setBaseSpAtk(100);
        DUMMY_DEX.setBaseSpDef(100);
        DUMMY_DEX.setBaseSpeed(100);
        DUMMY_DEX.setHiddenAbility(DUMMY_ABILITY);
        DUMMY_DEX.setAbilities(Set.of(DUMMY_ABILITY));
    }

    private static Pokemon getDummyPokemon(int stats) {
        final Pokemon mon = new Pokemon(DUMMY_DEX, 100, dummyEVs, Nature.QUIRKY, DUMMY_ABILITY);
        mon.setNickname("Dummy");
        mon.setMaxHP(3 * stats);
        mon.setCurrentHP(3 * stats);
        mon.setAttack(stats);
        mon.setDefense(stats);
        mon.setSpecialAttack(stats);
        mon.setSpecialDefense(stats);
        mon.setSpeed(stats);
        return mon;
    }

    public static Pokemon getDummyPokemon() {
        return getDummyPokemon(100);
    }

}
