package org.shorts.model.pokemon;

import java.util.HashSet;
import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.Nature;
import org.shorts.model.Sex;
import org.shorts.model.abilities.Ability;
import org.shorts.model.abilities.IgnorableAbility;
import org.shorts.model.abilities.NullifyingAbility;
import org.shorts.model.items.HeldItem;
import org.shorts.model.moves.Move;
import org.shorts.model.status.Status;
import org.shorts.model.status.volatilestatus.VolatileStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Guts.GUTS;
import static org.shorts.model.abilities.Levitate.LEVITATE;
import static org.shorts.model.status.volatilestatus.VolatileStatus.VolatileStatusType.GROUNDED;

public class Pokemon {

    private String pokedexNo;

    private String originalTrainer;
    private String nickname;
    private String speciesName;
    private Set<Type> types;
    private Ability ability;
    private Nature nature;
    private Move[] moves = new Move[4];

    private Sex sex;
    private int level;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int stageAttack;
    private int defense;
    private int stageDefense;
    private int specialAttack;
    private int stageSpecialAttack;
    private int specialDefense;
    private int stageSpecialDefense;
    private int speed;
    private int stageSpeed;
    private Status status = Status.NONE;
    private final Set<VolatileStatus> volatileStatuses = new HashSet<>();
    private HeldItem heldItem;

    protected Pokemon(String pokedexNo, String nickname, String speciesName, Set<Type> types, Ability ability) {
        this.pokedexNo = pokedexNo;
        this.nickname = nickname == null || nickname.isBlank() ? speciesName : nickname;
        this.speciesName = speciesName;
        this.types = types;
        this.ability = ability;
        this.ability.onGainAbility(this);
    }

    public void changeAttack(int stages) {
        this.stageAttack += stages;
        if (this.stageAttack > 6) {
            this.stageAttack = 6;
        }
    }

    public void changeDefense(int stages) {
        this.stageDefense += stages;
        if (this.stageDefense > 6) {
            this.stageDefense = 6;
        }
    }

    public void changeSpecialAttack(int stages) {
        this.stageSpecialAttack += stages;
        if (this.stageSpecialAttack > 6) {
            this.stageSpecialAttack = 6;
        }
    }

    public void changeSpecialDefense(int stages) {
        this.stageSpecialDefense += stages;
        if (this.stageSpecialDefense > 6) {
            this.stageSpecialDefense = 6;
        }
    }

    public void changeSpeed(int stages) {
        this.stageSpeed += stages;
        if (this.stageSpeed > 6) {
            this.stageSpeed = 6;
        }
    }

    public String getPokedexNo() {
        return pokedexNo;
    }

    public void setPokedexNo(String pokedexNo) {
        this.pokedexNo = pokedexNo;
    }

    public String getOriginalTrainer() {
        return originalTrainer;
    }

    public void setOriginalTrainer(String originalTrainer) {
        this.originalTrainer = originalTrainer;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Nature getNature() {
        return nature;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        if (maxHP < 1) {
            throw new IllegalArgumentException("Max HP must be at least 1");
        }
        this.maxHP = maxHP;
    }

    public int getAttack() {
        double multiplier = (2 + this.stageAttack) * 0.5;
        if (this.getStatus() == Status.BURN && !this.getAbility().equals(GUTS)) {
            multiplier *= 0.5;
        }
        if (this.stageAttack >= 0) {
            return (int) (this.attack * multiplier);
        } else {
            return (int) (this.attack / multiplier);
        }
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        double multiplier = (2 + this.stageDefense) * 0.5;
        if (this.stageDefense >= 0) {
            return (int) (this.defense * multiplier);
        } else {
            return (int) (this.defense / multiplier);
        }
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        double multiplier = (2 + this.stageSpecialAttack) * 0.5;
        if (this.stageSpecialAttack >= 0) {
            return (int) (this.specialAttack * multiplier);
        } else {
            return (int) (this.specialAttack / multiplier);
        }
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        double multiplier = (2 + this.stageSpecialDefense) * 0.5;
        if (this.stageSpecialDefense >= 0) {
            return (int) (this.specialDefense * multiplier);
        } else {
            return (int) (this.specialDefense / multiplier);
        }
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getSpeed() {
        double multiplier = (2 + this.stageSpeed) * 0.5;
        if (this.getStatus() == Status.PARALYZE) {
            multiplier *= 0.5;
        }
        if (this.stageSpeed >= 0) {
            return (int) (this.speed * multiplier);
        } else {
            return (int) (this.speed / multiplier);
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.currentHP = currentHP - damage;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    public void heal(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health restored cannot be negative");
        }
        this.currentHP = currentHP + health;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getStageAttack() {
        return stageAttack;
    }

    public void setStageAttack(int stageAttack) {
        this.stageAttack = stageAttack;
    }

    public int getStageDefense() {
        return stageDefense;
    }

    public void setStageDefense(int stageDefense) {
        this.stageDefense = stageDefense;
    }

    public int getStageSpecialAttack() {
        return stageSpecialAttack;
    }

    public void setStageSpecialAttack(int stageSpecialAttack) {
        this.stageSpecialAttack = stageSpecialAttack;
    }

    public int getStageSpecialDefense() {
        return stageSpecialDefense;
    }

    public void setStageSpecialDefense(int stageSpecialDefense) {
        this.stageSpecialDefense = stageSpecialDefense;
    }

    public int getStageSpeed() {
        return stageSpeed;
    }

    public void setStageSpeed(int stageSpeed) {
        this.stageSpeed = stageSpeed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<VolatileStatus> getVolatileStatuses() {
        return volatileStatuses;
    }

    public HeldItem getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(HeldItem heldItem) {
        this.heldItem = heldItem;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Move[] getMoves() {
        return moves;
    }

    public void setMoves(Move[] moves) {
        this.moves = moves;
    }

    public boolean isGrounded() {
        if (volatileStatuses.stream().anyMatch(vs -> vs.getType() == GROUNDED)) {
            return true;
        } else {
            return !this.types.contains(Type.FLYING) && !this.ability.equals(LEVITATE) && !this.getHeldItem()
                .getName()
                .equals("Air Balloon");
        }
    }

    public boolean hasFainted() {
        return currentHP == 0;
    }

    //    public static Pokemon fromPokedexEntry(PokedexEntry entry) {
    //        return new Pokemon(
    //            entry.getPokedexNo(),
    //            entry.getSpeciesName(),
    //            entry.getSpeciesName(),
    //            entry.getTypes(),
    //            entry.getAbilities().stream().findFirst()
    //                .get());
    //    }

    public void afterEntry(Pokemon opponent, Battle battle) {
        ability.afterEntry(this, opponent, battle);
        heldItem.afterEntry(this, opponent, battle);
    }

    public double onMovePowerCalc(Pokemon opponent, Battle battle, Move move) {
        return ability.onMovePowerCalc(this, opponent, battle, move) * heldItem.onMovePowerCalc(
            this,
            opponent,
            battle,
            move);
    }

    public void beforeAttack(Pokemon opponent, Battle battle, Move move) {
        ability.beforeAttack(this, opponent, battle, move);
        heldItem.beforeAttack(this, opponent, battle, move);
    }

    public void afterAttack(Pokemon opponent, Battle battle) {
        ability.afterAttack(this, opponent, battle);
        heldItem.afterAttack(this, opponent, battle);
    }

    public void afterDrop(Pokemon opponent, Battle battle) {
        ability.afterDrop(this, opponent, battle);
        heldItem.afterDrop(this, opponent, battle);
    }

    public double beforeHit(Pokemon opponent, Battle battle, Move move) {
        double abilityMultiplier = 1;
        if (!(ability instanceof IgnorableAbility && opponent.getAbility() instanceof NullifyingAbility)) {
            abilityMultiplier *= ability.beforeHit(this, opponent, battle, move);
        }
        if (abilityMultiplier == 0) {
            return abilityMultiplier;
        } else {
            /* TODO: Ability goes first because of cases where either ability or item nullifies damage, e.g. Levitate and Shuca Berry.
                My hope is that this will prevent items from being wasted.*/
            return abilityMultiplier * heldItem.beforeHit(this, opponent, battle, move);
        }
    }

    public void afterHit(Pokemon opponent, Battle battle, int previousHP) {
        ability.afterHit(this, opponent, battle, previousHP);
        heldItem.afterHit(this, opponent, battle, previousHP);
    }

    public void afterStatus(Pokemon opponent, Battle battle) {
        //TODO: HeldItem is first here because Rawst Berry will activate before Water Veil. Is this how it works for all abilities/items?
        heldItem.afterStatus(this, opponent, battle);
        ability.afterStatus(this, opponent, battle);
    }

    public void beforeTurn(Pokemon opponent, Battle battle) {
        ability.beforeTurn(this, opponent, battle);
        heldItem.beforeTurn(this, opponent, battle);
    }

    public void afterTurn(Pokemon opponent, Battle battle) {
        ability.afterTurn(this, opponent, battle);
        heldItem.afterTurn(this, opponent, battle);
    }

    public void afterFaint(Pokemon opponent, Battle battle) {
        ability.afterFaint(this, opponent, battle);
        heldItem.afterFaint(this, opponent, battle);
    }

    public void afterKO(Pokemon opponent, Battle battle) {
        ability.afterKO(this, opponent, battle);
        heldItem.afterKO(this, opponent, battle);
    }

    public void beforeSwitchOut(Pokemon opponent, Battle battle) {
        ability.beforeSwitchOut(this, opponent, battle);
        heldItem.beforeSwitchOut(this, opponent, battle);
    }
}
