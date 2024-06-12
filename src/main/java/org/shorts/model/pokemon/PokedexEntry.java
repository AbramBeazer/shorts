package org.shorts.model.pokemon;

import org.shorts.model.Sex;
import org.shorts.model.types.Type;

public class PokedexEntry {

    private final int pokedexNo;

    private final String speciesName;

    private final String speciesDescription;
    private final String entryText;
    private final Type type1;
    private final Type type2;
    private final String ability1;
    private final String ability2;
    private final String hiddenAbility;
    private final Sex singleSex;
    private final int baseHP;
    private final int baseAtk;
    private final int baseDef;
    private final int baseSpAtk;
    private final int baseSpDef;
    private final int baseSpeed;
    private final double height;
    private final double weight;

    public PokedexEntry(PokedexEntryBuilder builder) {
        this.pokedexNo = builder.pokedexNo;
        this.speciesName = builder.speciesName;
        this.speciesDescription = builder.speciesDescription;
        this.entryText = builder.entryText;
        this.type1 = builder.type1;
        this.type2 = builder.type2;
        this.ability1 = builder.ability1;
        this.ability2 = builder.ability2;
        this.hiddenAbility = builder.hiddenAbility;
        this.singleSex = builder.singleSex;
        this.baseHP = builder.baseHP;
        this.baseAtk = builder.baseAtk;
        this.baseDef = builder.baseDef;
        this.baseSpAtk = builder.baseSpAtk;
        this.baseSpDef = builder.baseSpDef;
        this.baseSpeed = builder.baseSpeed;
        this.height = builder.height;
        this.weight = builder.weight;
    }

    //    private Map<String, Integer> evolvesFrom;
    //
    //    private Map<String, Integer> evolvesTo;
    //    private int yieldHP;
    //
    //    private int yieldAtk;
    //
    //    private int yieldDef;
    //
    //    private int yieldSpAtk;
    //
    //    private int yieldSpDef;
    //
    //    private int yieldSpeed;
    //
    //    private int catchRate;
    //    private int baseFriendship;
    //    private int baseYieldEXP;
    //
    //    private GrowthRate growthRate;
    //
    //    private Set<EggGroup> eggGroups;
    //
    //    private double percentageMale = 0.5;
    //
    //    private int eggCycles;
    //
    //    private Map<Integer, Move> levelUpMoves;
    //
    //    private Set<Move> eggMoves;
    //
    //    private Set<Move> learnableTMs;

    public int getPokedexNo() {
        return pokedexNo;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getSpeciesDescription() {
        return speciesDescription;
    }

    public String getEntryText() {
        return entryText;
    }

    public Type getType1() {
        return type1;
    }

    public Type getType2() {
        return type2;
    }

    public String getAbility1() {
        return ability1;
    }

    public String getAbility2() {
        return ability2;
    }

    public String getHiddenAbility() {
        return hiddenAbility;
    }

    public Sex getSingleSex() {
        return singleSex;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public int getBaseSpAtk() {
        return baseSpAtk;
    }

    public int getBaseSpDef() {
        return baseSpDef;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getBST() {
        return baseHP + baseAtk + baseDef + baseSpAtk + baseSpDef + baseSpeed;
    }

    public static class PokedexEntryBuilder {

        private int pokedexNo;

        private String speciesName;

        private String speciesDescription;
        private String entryText;
        private Type type1;
        private Type type2;
        private String ability1;
        private String ability2;
        private String hiddenAbility;
        private Sex singleSex;
        private int baseHP;
        private int baseAtk;
        private int baseDef;
        private int baseSpAtk;
        private int baseSpDef;
        private int baseSpeed;
        private double height;
        private double weight;

        public static PokedexEntryBuilder createNewInstance() {
            return new PokedexEntryBuilder();
        }

        private PokedexEntryBuilder() {
        }

        public PokedexEntryBuilder setPokedexNo(int pokedexNo) {
            this.pokedexNo = pokedexNo;
            return this;
        }

        public PokedexEntryBuilder setSpeciesName(String speciesName) {
            this.speciesName = speciesName;
            return this;
        }

        public PokedexEntryBuilder setSpeciesDescription(String speciesDescription) {
            this.speciesDescription = speciesDescription;
            return this;
        }

        public PokedexEntryBuilder setEntryText(String entryText) {
            this.entryText = entryText;
            return this;
        }

        public PokedexEntryBuilder setType1(Type type1) {
            this.type1 = type1;
            return this;
        }

        public PokedexEntryBuilder setType2(Type type2) {
            this.type2 = type2;
            return this;
        }

        public PokedexEntryBuilder setAbility1(String ability1) {
            this.ability1 = ability1;
            return this;
        }

        public PokedexEntryBuilder setAbility2(String ability2) {
            this.ability2 = ability2;
            return this;
        }

        public PokedexEntryBuilder setHiddenAbility(String hiddenAbility) {
            this.hiddenAbility = hiddenAbility;
            return this;
        }

        public PokedexEntryBuilder setSingleSex(Sex singleSex) {
            this.singleSex = singleSex;
            return this;
        }

        public PokedexEntryBuilder setBaseHP(int baseHP) {
            this.baseHP = baseHP;
            return this;
        }

        public PokedexEntryBuilder setBaseAtk(int baseAtk) {
            this.baseAtk = baseAtk;
            return this;
        }

        public PokedexEntryBuilder setBaseDef(int baseDef) {
            this.baseDef = baseDef;
            return this;
        }

        public PokedexEntryBuilder setBaseSpAtk(int baseSpAtk) {
            this.baseSpAtk = baseSpAtk;
            return this;
        }

        public PokedexEntryBuilder setBaseSpDef(int baseSpDef) {
            this.baseSpDef = baseSpDef;
            return this;
        }

        public PokedexEntryBuilder setBaseSpeed(int baseSpeed) {
            this.baseSpeed = baseSpeed;
            return this;
        }

        public PokedexEntryBuilder setHeight(double height) {
            this.height = height;
            return this;
        }

        public PokedexEntryBuilder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public PokedexEntry build() {
            return new PokedexEntry(this);
        }
    }
}
