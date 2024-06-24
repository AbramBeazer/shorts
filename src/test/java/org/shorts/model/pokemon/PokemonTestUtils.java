package org.shorts.model.pokemon;

import org.shorts.model.Nature;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.DummyAbility.DUMMY_ABILITY;

public final class PokemonTestUtils {

    public static final PokedexEntry DUMMY_DEX;
    public static final int[] DUMMY_EVS = new int[] { 0, 0, 0, 0, 0, 0 };

    static {
        PokedexEntry.PokedexEntryBuilder builder = PokedexEntry.PokedexEntryBuilder.createNewInstance();
        builder.setSpeciesName("TEST MON");
        builder.setHeight(1);
        builder.setWeight(100);
        builder.setBaseHP(300);
        builder.setBaseAtk(100);
        builder.setBaseDef(100);
        builder.setBaseSpAtk(100);
        builder.setBaseSpDef(100);
        builder.setBaseSpeed(100);
        builder.setHiddenAbility(DUMMY_ABILITY.getName());
        builder.setAbility1(DUMMY_ABILITY.getName());
        builder.setType1(Type.NORMAL);
        DUMMY_DEX = builder.build();
    }

    private static Pokemon getDummyPokemon(int stats) {
        final Pokemon mon = new Pokemon(DUMMY_DEX, 100, DUMMY_EVS, Nature.QUIRKY, DUMMY_ABILITY);
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
