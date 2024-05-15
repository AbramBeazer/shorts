package org.shorts.model.pokemon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.Nature;
import org.shorts.model.Sex;
import org.shorts.model.abilities.Ability;
import org.shorts.model.abilities.IgnorableAbility;
import org.shorts.model.abilities.NullifyingAbility;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.NoItem;
import org.shorts.model.moves.Move;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Guts.GUTS;
import static org.shorts.model.abilities.Levitate.LEVITATE;
import static org.shorts.model.abilities.trapping.ArenaTrap.ARENA_TRAP;
import static org.shorts.model.abilities.trapping.MagnetPull.MAGNET_PULL;
import static org.shorts.model.abilities.trapping.ShadowTag.SHADOW_TAG;
import static org.shorts.model.items.AirBalloon.AIR_BALLOON;
import static org.shorts.model.items.ShedShell.SHED_SHELL;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.CANT_ESCAPE;
import static org.shorts.model.status.VolatileStatusType.GROUNDED;
import static org.shorts.model.status.VolatileStatusType.MAGNET_LEVITATION;
import static org.shorts.model.status.VolatileStatusType.NO_RETREAT;
import static org.shorts.model.status.VolatileStatusType.OCTOLOCKED;

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
    private final Map<VolatileStatusType, VolatileStatus> volatileStatuses = new HashMap<>();
    private HeldItem heldItem = NoItem.NO_ITEM;

    protected Pokemon(String pokedexNo, String nickname, String speciesName, Set<Type> types, Ability ability) {
        this.pokedexNo = pokedexNo;
        this.nickname = nickname == null || nickname.isBlank() ? speciesName : nickname;
        this.speciesName = speciesName;
        this.types = types;
        this.ability = ability;
        this.ability.onGainAbility(this);
    }

    public Pokemon(int currentHP, int maxHP) {
        this.currentHP = currentHP;
        this.maxHP = maxHP;
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

    private void setMoves(Move[] moves) {
        this.moves = moves;
    }

    public void setMoves(List<Move> moves) {
        setMoves(moves.toArray(new Move[0]));
    }

    public boolean isGrounded() {
        if (hasVolatileStatus(GROUNDED)) {
            return true;
        } else if (hasVolatileStatus(MAGNET_LEVITATION)) {
            return false;
        } else {
            return !(this.types.contains(Type.FLYING) || this.getHeldItem().equals(AIR_BALLOON)
                || (this.ability.equals(LEVITATE) && !hasVolatileStatus(ABILITY_IGNORED)));
            //TODO: Should I be checking for suppression as well?
        }
    }

    public boolean isTrapped(Battle battle) {
        Pokemon opponent = battle.getOpposingLead(this);
        if (getHeldItem() == SHED_SHELL || getTypes().contains(Type.GHOST)) {
            return false;
        } else if (opponent.getAbility() == MAGNET_PULL && this.getTypes().contains(Type.STEEL)) {
            return true;
        } else if (opponent.getAbility() == ARENA_TRAP && this.isGrounded()) {
            return true;
        } else if (opponent.getAbility() == SHADOW_TAG && this.getAbility() != SHADOW_TAG) {
            return true;
        } else {
            return hasVolatileStatus(CANT_ESCAPE) || hasVolatileStatus(NO_RETREAT) || hasVolatileStatus(OCTOLOCKED);
        }
    }

    public VolatileStatus getVolatileStatus(VolatileStatusType type) {
        return volatileStatuses.get(type);
    }

    public boolean hasVolatileStatus(VolatileStatusType type) {
        return volatileStatuses.containsKey(type);
    }

    public void addVolatileStatus(VolatileStatus status) {
        volatileStatuses.put(status.getType(), status);
    }

    public void removeVolatileStatus(VolatileStatusType type) {
        if (this.hasVolatileStatus(type)) {
            volatileStatuses.remove(type);
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

    public double beforeAttack(Pokemon opponent, Battle battle, Move move) {
        final double abilityMultiplier = ability.beforeAttack(this, opponent, battle, move);
        if (abilityMultiplier == 0) {
            return 0;
        } else {
            return abilityMultiplier * heldItem.beforeAttack(this, opponent, battle, move);
        }
        //Again, I don't want to consume an item if the ability's going to nullify the effect anyway.
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

    public void afterHit(Pokemon opponent, Battle battle, int previousHP, Move move) {
        ability.afterHit(this, opponent, battle, previousHP, move);
        heldItem.afterHit(this, opponent, battle, previousHP, move);
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
