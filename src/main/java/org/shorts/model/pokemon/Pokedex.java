package org.shorts.model.pokemon;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.shorts.model.Sex;
import org.shorts.model.types.Type;

import static org.apache.commons.lang3.StringUtils.isBlank;

public final class Pokedex {

    private static final Map<String, PokedexEntry> DEX = new HashMap<>();

    private static final int DEX_NO = 0;
    private static final int SPECIES = 1;
    private static final int TYPE = 2;
    private static final int TYPE2 = 3;
    private static final int ABILITY = 4;
    private static final int ABILITY2 = 5;
    private static final int HIDDEN = 6;
    private static final int HEIGHT = 7;
    private static final int WEIGHT = 8;
    private static final int HP = 9;
    private static final int ATK = 10;
    private static final int DEF = 11;
    private static final int SPATK = 12;
    private static final int SPDEF = 13;
    private static final int SPEED = 14;
    private static final int SEX = 15;

    public static void create() throws Exception {
        if (DEX.size() == 0) {
            try (InputStream stream = Pokedex.class.getClassLoader().getResourceAsStream("pokedex.csv")) {
                Objects.requireNonNull(stream, "Input stream cannot be null!");
                final String text = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                final String[] lines = text.split("\r\n");

                int dexNo = 1;
                for (String line : lines) {
                    String[] fields = line.split(",");
                    if (!isBlank(fields[DEX_NO])) {
                        dexNo = Integer.parseInt(fields[DEX_NO]);
                    }
                    PokedexEntry.PokedexEntryBuilder builder = PokedexEntry.PokedexEntryBuilder.createNewInstance();
                    builder.setPokedexNo(dexNo);
                    builder.setSpeciesName(fields[SPECIES]);

                    builder.setType1(Type.fromString(fields[TYPE]));
                    if (!isBlank(fields[TYPE2])) {
                        builder.setType2(Type.fromString(fields[TYPE2]));
                    }

                    builder.setAbility1(fields[ABILITY]);

                    if (!isBlank(fields[ABILITY2])) {
                        builder.setAbility2(fields[ABILITY2]);
                    }

                    if (!isBlank(fields[HIDDEN])) {
                        builder.setHiddenAbility(fields[HIDDEN]);
                    }

                    builder.setHeight(Double.parseDouble(fields[HEIGHT]));
                    builder.setWeight(Double.parseDouble(fields[WEIGHT]));

                    builder.setBaseHP(Integer.parseInt(fields[HP]));
                    builder.setBaseAtk(Integer.parseInt(fields[ATK]));
                    builder.setBaseDef(Integer.parseInt(fields[DEF]));
                    builder.setBaseSpAtk(Integer.parseInt(fields[SPATK]));
                    builder.setBaseSpDef(Integer.parseInt(fields[SPDEF]));
                    builder.setBaseSpeed(Integer.parseInt(fields[SPEED]));

                    if (fields.length >= 16) {
                        final int code = Integer.parseInt(fields[SEX]);
                        Optional<Sex> singleSex = Arrays.stream(Sex.values())
                            .filter(s -> s.ordinal() == code)
                            .findFirst();
                        singleSex.ifPresent(builder::setSingleSex);
                    }

                    final PokedexEntry entry = builder.build();
                    DEX.put(entry.getSpeciesName(), entry);
                }
            }
        }
    }

    public static PokedexEntry get(String species) {
        return DEX.get(species);
    }
}
