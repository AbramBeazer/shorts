package org.shorts.model.pokemon;

import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.model.Nature;
import org.shorts.model.PokedexEntry;
import org.shorts.model.Status;
import org.shorts.model.abilities.Ability;
import org.shorts.model.items.HeldItem;
import org.shorts.model.moves.Move;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Levitate.LEVITATE;

public class Pokemon {

    private String pokedexNo;

    private String originalTrainer;
    private String nickname;
    private String speciesName;
    private Set<Type> types;
    private Ability ability;
    private Nature nature;
    private Move[] moves = new Move[4];
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
    //TODO: Add stackable statuses (confused, cursed, in love, etc.)
    private HeldItem heldItem;

    private boolean usingDig = false;
    private boolean usingFly = false;
    private boolean usingDive = false;
    private boolean usingBounce = false;

    private boolean groundedOverride = false;

    private int sleepCounter = 0;

    protected Pokemon(String pokedexNo, String nickname, String speciesName, Set<Type> types) {
        this.pokedexNo = pokedexNo;
        this.nickname = nickname == null || nickname.isBlank() ? speciesName : nickname;
        this.speciesName = speciesName;
        this.types = types;
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

    public void setMoves(Move[] moves) {
        this.moves = moves;
    }

    public int getSleepCounter() {
        return sleepCounter;
    }

    public void setSleepCounter(int sleepCounter) {
        this.sleepCounter = sleepCounter;
    }

    public boolean isGrounded() {
        if (groundedOverride) {
            return true;
        } else {
            return !this.types.contains(Type.FLYING) && !this.ability.equals(LEVITATE)
                && !this.getHeldItem().getName().equals("Air Balloon");
        }
    }

    public boolean isUsingDig() {
        return usingDig;
    }

    public void setUsingDig(boolean usingDig) {
        this.usingDig = usingDig;
    }

    public boolean isUsingFly() {
        return usingFly;
    }

    public void setUsingFly(boolean usingFly) {
        this.usingFly = usingFly;
    }

    public boolean isUsingDive() {
        return usingDive;
    }

    public void setUsingDive(boolean usingDive) {
        this.usingDive = usingDive;
    }

    public boolean isUsingBounce() {
        return usingBounce;
    }

    public void setUsingBounce(boolean usingBounce) {
        this.usingBounce = usingBounce;
    }

    public static Pokemon fromPokedexEntry(PokedexEntry entry) {
        return new Pokemon(entry.getPokedexNo(), entry.getSpeciesName(), entry.getSpeciesName(), entry.getTypes());
    }

    public void afterEntry(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterEntry(self, opponent, battle);
        heldItem.afterEntry(self, opponent, battle);
    }

    public void beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Integer damage, Type moveType) {
        ability.beforeAttack(self, opponent, battle, damage, moveType);
        heldItem.beforeAttack(self, opponent, battle, damage, moveType);
    }

    public void afterAttack(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterAttack(self, opponent, battle);
        heldItem.afterAttack(self, opponent, battle);
    }

    public void afterDrop(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterDrop(self, opponent, battle);
        heldItem.afterDrop(self, opponent, battle);
    }

    public void beforeHit(Pokemon self, Pokemon opponent, Battle battle, Integer damage, Type moveType) {
        /* TODO: Ability goes first because of cases where either ability or item nullifies damage, e.g. Levitate and Shuca Berry.
            My hope is that this will prevent items from being wasted.*/
        ability.beforeHit(self, opponent, battle, damage, moveType);
        heldItem.beforeHit(self, opponent, battle, damage, moveType);
    }

    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP) {
        ability.afterHit(self, opponent, battle, previousHP);
        heldItem.afterHit(self, opponent, battle, previousHP);
    }

    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterStatus(self, opponent, battle);
        heldItem.afterStatus(self, opponent, battle);
    }

    public void beforeTurn(Pokemon self, Pokemon opponent, Battle battle) {
        ability.beforeTurn(self, opponent, battle);
        heldItem.beforeTurn(self, opponent, battle);
    }

    public void afterTurn(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterTurn(self, opponent, battle);
        heldItem.afterTurn(self, opponent, battle);
    }

    public void afterFaint(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterFaint(self, opponent, battle);
        heldItem.afterFaint(self, opponent, battle);
    }

    public void afterKO(Pokemon self, Pokemon opponent, Battle battle) {
        ability.afterKO(self, opponent, battle);
        heldItem.afterKO(self, opponent, battle);
    }
}
