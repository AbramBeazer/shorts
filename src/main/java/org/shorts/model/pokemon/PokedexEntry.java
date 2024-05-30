package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.types.Type;

public class PokedexEntry {

    private String pokedexNo;

    private String speciesName;

    private String speciesDescription;
    private String entryText;
    private Set<Type> types;
    private Set<Ability> abilities;
    private Ability hiddenAbility;
    private Boolean genderless;
    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpAtk;
    private int baseSpDef;
    private int baseSpeed;
    private double height;
    private double weight;

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

    public String getPokedexNo() {
        return pokedexNo;
    }

    public void setPokedexNo(String pokedexNo) {
        this.pokedexNo = pokedexNo;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getSpeciesDescription() {
        return speciesDescription;
    }

    public void setSpeciesDescription(String speciesDescription) {
        this.speciesDescription = speciesDescription;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<Ability> abilities) {
        this.abilities = abilities;
    }

    public Ability getHiddenAbility() {
        return hiddenAbility;
    }

    public void setHiddenAbility(Ability hiddenAbility) {
        this.hiddenAbility = hiddenAbility;
    }

    public boolean getGenderless() {
        return Boolean.TRUE.equals(genderless);
    }

    public void setGenderless(boolean genderless) {
        this.genderless = Boolean.TRUE.equals(genderless) ? Boolean.TRUE : null;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(int baseAtk) {
        this.baseAtk = baseAtk;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(int baseDef) {
        this.baseDef = baseDef;
    }

    public int getBaseSpAtk() {
        return baseSpAtk;
    }

    public void setBaseSpAtk(int baseSpAtk) {
        this.baseSpAtk = baseSpAtk;
    }

    public int getBaseSpDef() {
        return baseSpDef;
    }

    public void setBaseSpDef(int baseSpDef) {
        this.baseSpDef = baseSpDef;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getBST() {
        return baseHP + baseAtk + baseDef + baseSpAtk + baseSpDef + baseSpeed;
    }
}
