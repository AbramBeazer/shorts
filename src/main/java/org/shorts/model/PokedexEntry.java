package org.shorts.model;

import java.util.Map;
import java.util.Set;

import org.shorts.model.abilities.Ability;
import org.shorts.model.moves.Move;
import org.shorts.model.types.Type;

public class PokedexEntry {

    private String pokedexNo;
    private Map<String, Integer> evolvesFrom;

    private Map<String, Integer> evolvesTo;

    private String speciesName;

    private String speciesDescription;
    private String entryText;
    private Set<Type> types;
    private Set<Ability> abilities;
    private Ability hiddenAbility;
    private double height;
    private double weight;
    private int yieldHP;

    private int yieldAtk;

    private int yieldDef;

    private int yieldSpAtk;

    private int yieldSpDef;

    private int yieldSpeed;

    private int catchRate;
    private int baseFriendship;
    private int baseYieldEXP;

    private GrowthRate growthRate;

    private Set<EggGroup> eggGroups;

    private Boolean genderless;
    private double percentageMale = 0.5;

    private int eggCycles;

    private Map<Integer, Move> levelUpMoves;

    private Set<Move> eggMoves;

    private Set<Move> learnableTMs;

    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpAtk;
    private int baseSpDef;
    private int baseSpeed;

    public String getPokedexNo() {
        return pokedexNo;
    }

    public Map<String, Integer> getEvolvesFrom() {
        return evolvesFrom;
    }

    public Map<String, Integer> getEvolvesTo() {
        return evolvesTo;
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

    public Set<Type> getTypes() {
        return types;
    }

    public Set<Ability> getAbilities() {
        return abilities;
    }

    public Ability getHiddenAbility() {
        return hiddenAbility;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getYieldHP() {
        return yieldHP;
    }

    public int getYieldAtk() {
        return yieldAtk;
    }

    public int getYieldDef() {
        return yieldDef;
    }

    public int getYieldSpAtk() {
        return yieldSpAtk;
    }

    public int getYieldSpDef() {
        return yieldSpDef;
    }

    public int getYieldSpeed() {
        return yieldSpeed;
    }

    public int getCatchRate() {
        return catchRate;
    }

    public int getBaseFriendship() {
        return baseFriendship;
    }

    public int getBaseYieldEXP() {
        return baseYieldEXP;
    }

    public GrowthRate getGrowthRate() {
        return growthRate;
    }

    public Set<EggGroup> getEggGroups() {
        return eggGroups;
    }

    public Boolean getGenderless() {
        return genderless;
    }

    public double getPercentageMale() {
        return percentageMale;
    }

    public int getEggCycles() {
        return eggCycles;
    }

    public Map<Integer, Move> getLevelUpMoves() {
        return levelUpMoves;
    }

    public Set<Move> getEggMoves() {
        return eggMoves;
    }

    public Set<Move> getLearnableTMs() {
        return learnableTMs;
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

    public int getBST() {
        return baseHP + baseAtk + baseDef + baseSpAtk + baseSpDef + baseSpeed;
    }
}
