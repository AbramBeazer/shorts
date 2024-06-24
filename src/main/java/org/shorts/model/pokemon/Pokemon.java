package org.shorts.model.pokemon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.shorts.battle.Battle;
import org.shorts.battle.Weather;
import org.shorts.model.Nature;
import org.shorts.model.Sex;
import org.shorts.model.StatEnum;
import org.shorts.model.abilities.Ability;
import org.shorts.model.abilities.IgnorableAbility;
import org.shorts.model.abilities.NullifyingAbility;
import org.shorts.model.abilities.Pickup;
import org.shorts.model.abilities.UnsuppressableAbility;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.NoItem;
import org.shorts.model.moves.Move;
import org.shorts.model.status.AutotomizedStatus;
import org.shorts.model.status.HelpingHandStatus;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.Main.RANDOM;
import static org.shorts.model.StatEnum.ATK;
import static org.shorts.model.StatEnum.DEF;
import static org.shorts.model.StatEnum.HP;
import static org.shorts.model.StatEnum.SPATK;
import static org.shorts.model.StatEnum.SPDEF;
import static org.shorts.model.StatEnum.SPEED;
import static org.shorts.model.abilities.Contrary.CONTRARY;
import static org.shorts.model.abilities.Levitate.LEVITATE;
import static org.shorts.model.abilities.trapping.ArenaTrap.ARENA_TRAP;
import static org.shorts.model.abilities.trapping.MagnetPull.MAGNET_PULL;
import static org.shorts.model.abilities.trapping.ShadowTag.SHADOW_TAG;
import static org.shorts.model.items.AirBalloon.AIR_BALLOON;
import static org.shorts.model.items.FloatStone.FLOAT_STONE;
import static org.shorts.model.items.ShedShell.SHED_SHELL;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.AUTOTOMIZED;
import static org.shorts.model.status.VolatileStatusType.CANT_ESCAPE;
import static org.shorts.model.status.VolatileStatusType.GROUNDED;
import static org.shorts.model.status.VolatileStatusType.HELPING_HAND;
import static org.shorts.model.status.VolatileStatusType.MAGNET_LEVITATION;
import static org.shorts.model.status.VolatileStatusType.NO_RETREAT;
import static org.shorts.model.status.VolatileStatusType.OCTOLOCKED;
import static org.shorts.model.status.VolatileStatusType.ROOTED;

public class Pokemon {

    @Deprecated
    private String pokedexNo;

    private PokedexEntry pokedexEntry;
    private String originalTrainer;
    private String nickname;
    @Deprecated
    private String speciesName;
    private Set<Type> types;
    private Ability ability;
    private Nature nature;
    @SuppressWarnings("checkstyle:MemberName")
    private int[] ev;
    @SuppressWarnings("checkstyle:MemberName")
    private int[] iv = { 31, 31, 31, 31, 31, 31 };

    private Move[] moves = new Move[4];
    private Move lastMoveUsed;
    private boolean movedThisTurn;
    private Sex sex;
    private int level;
    private int maxHP;
    private int currentHP;
    private int attack;
    private int stageAttack = 0;
    private int defense;
    private int stageDefense = 0;
    private int specialAttack;
    private int stageSpecialAttack = 0;
    private int specialDefense;
    private int stageSpecialDefense = 0;
    private int speed;
    private int stageSpeed = 0;
    private int stageAccuracy = 0;
    private int stageEvasion = 0;
    private Status status = Status.NONE;
    private final Map<VolatileStatusType, VolatileStatus> volatileStatuses = new HashMap<>();
    private HeldItem heldItem = NoItem.NO_ITEM;
    private HeldItem consumedItem = NoItem.NO_ITEM;
    private byte happiness;
    private int turnsInBattle;

    protected Pokemon(String pokedexNo, String nickname, String speciesName, Set<Type> types, Ability ability) {
        this.pokedexNo = pokedexNo;
        this.nickname = nickname == null || nickname.isBlank() ? speciesName : nickname;
        this.speciesName = speciesName;
        this.types = types;
        this.ability = ability;
        this.ability.onInitiate(this);
    }

    public Pokemon(int currentHP, int maxHP, Ability ability) {
        this.currentHP = currentHP;
        setMaxHP(maxHP);
        this.ability = ability;
    }

    public Pokemon(PokedexEntry pokedexEntry, int level, int[] effortValues, Nature nature, Ability ability) {
        this.pokedexEntry = pokedexEntry;
        this.setNature(nature);
        this.speciesName = pokedexEntry.getSpeciesName();
        this.types = pokedexEntry.getType2() == null
            ? Set.of(pokedexEntry.getType1())
            : Set.of(pokedexEntry.getType1(), pokedexEntry.getType2());
        this.happiness = Byte.MAX_VALUE;
        this.ev = effortValues;

        if (pokedexEntry.getSingleSex() == null) {
            this.sex = RANDOM.nextInt(2) == 0 ? Sex.FEMALE : Sex.MALE;
        } else {
            this.sex = pokedexEntry.getSingleSex();
        }

        if (ability.getName().equals(pokedexEntry.getHiddenAbility()) || ability.getName()
            .equals(pokedexEntry.getAbility1()) || ability.getName().equals(pokedexEntry.getAbility2())) {
            this.ability = ability;
        }
        this.level = Math.max(1, Math.min(100, level));

        if (pokedexEntry.getBaseHP() == 1) {
            this.maxHP = 1; //Special case for Shedinja.
        } else {
            this.maxHP =
                (((2 * pokedexEntry.getBaseHP() + iv[HP.ordinal()] + (ev[HP.ordinal()] / 4)) * level) / 100) + level
                    + 10;
        }
        this.currentHP = this.maxHP;

        this.attack =
            ((((2 * pokedexEntry.getBaseAtk() + iv[ATK.ordinal()] + (ev[ATK.ordinal()] / 4) * level) / 100) + 5)
                * nature.getMultiplier(ATK)) / 100;
        this.defense =
            ((((2 * pokedexEntry.getBaseDef() + iv[DEF.ordinal()] + (ev[DEF.ordinal()] / 4) * level) / 100) + 5)
                * nature.getMultiplier(DEF)) / 100;
        this.specialAttack =
            ((((2 * pokedexEntry.getBaseAtk() + iv[SPATK.ordinal()] + (ev[SPATK.ordinal()] / 4) * level) / 100) + 5)
                * nature.getMultiplier(SPATK)) / 100;
        this.specialDefense =
            ((((2 * pokedexEntry.getBaseDef() + iv[SPDEF.ordinal()] + (ev[SPDEF.ordinal()] / 4) * level) / 100) + 5)
                * nature.getMultiplier(SPDEF)) / 100;
        this.speed =
            ((((2 * pokedexEntry.getBaseAtk() + iv[SPEED.ordinal()] + (ev[SPEED.ordinal()] / 4) * level) / 100) + 5)
                * nature.getMultiplier(SPEED)) / 100;
    }

    public boolean canChangeStat(int change, StatEnum stat) {
        final int stage;
        switch (stat) {
            case ATK:
                stage = stageAttack;
                break;
            case DEF:
                stage = stageDefense;
                break;
            case SPATK:
                stage = stageSpecialAttack;
                break;
            case SPDEF:
                stage = stageSpecialDefense;
                break;
            case SPEED:
                stage = stageSpeed;
                break;
            case ACCURACY:
                stage = stageAccuracy;
                break;
            case EVASION:
                stage = stageEvasion;
                break;
            default:
                return false;
        }
        if (stage == 6) {
            if (change > 0) {
                return this.ability == CONTRARY;
            } else if (change < 0) {
                return this.ability != CONTRARY;
            }
        } else if (stage == -6) {
            if (change < 0) {
                return this.ability == CONTRARY;
            } else if (change > 0) {
                return this.ability != CONTRARY;
            }
        }
        return true;
    }

    public void changeStat(int stages, StatEnum stat) {
        switch (stat) {
            case ATK:
                changeAttack(stages);
                break;
            case DEF:
                changeDefense(stages);
                break;
            case SPATK:
                changeSpecialAttack(stages);
                break;
            case SPDEF:
                changeSpecialDefense(stages);
                break;
            case SPEED:
                changeSpeed(stages);
                break;
            case ACCURACY:
                changeAccuracy(stages);
                break;
            case EVASION:
                changeEvasion(stages);
                break;
            default:
        }
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

    public void changeAccuracy(int stages) {
        this.stageAccuracy += stages;
        if (this.stageAccuracy > 6) {
            this.stageAccuracy = 6;
        }
    }

    public void changeEvasion(int stages) {
        this.stageEvasion += stages;
        if (this.stageEvasion > 6) {
            this.stageEvasion = 6;
        }
    }

    @Deprecated
    public String getPokedexNo() {
        return pokedexNo;
    }

    @Deprecated
    public void setPokedexNo(String pokedexNo) {
        this.pokedexNo = pokedexNo;
    }

    public PokedexEntry getPokedexEntry() {
        return pokedexEntry;
    }

    public void setPokedexEntry(PokedexEntry pokedexEntry) {
        this.pokedexEntry = pokedexEntry;
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

    public String getDisplayName() {
        if (nickname == null) {
            return pokedexEntry.getSpeciesName();
        } else {
            return nickname + " (" + pokedexEntry.getSpeciesName() + ")";
        }
    }

    @Deprecated
    public String getSpeciesName() {
        return pokedexEntry != null ? pokedexEntry.getSpeciesName() : speciesName;
    }

    @Deprecated
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

    public double calculateAttack() {
        double multiplier = 1;
        multiplier *= ability.onCalculateAttack(this) * heldItem.onCalculateAttack(this);
        return this.attack * getStageMultiplier(stageAttack) * multiplier;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public double calculateDefense(Battle battle) {
        double multiplier = ability.onCalculateDefense(this) * heldItem.onCalculateDefense(this);
        if (types.contains(Type.ICE) && !battle.isWeatherSuppressed() && battle.getWeather() == Weather.SNOW) {
            multiplier *= 1.5;
        }
        return this.defense * getStageMultiplier(stageDefense) * multiplier;
    }

    public double calculateDefenseIgnoreStage(Battle battle) {
        double multiplier = ability.onCalculateDefense(this) * heldItem.onCalculateDefense(this);
        if (types.contains(Type.ICE) && !battle.isWeatherSuppressed() && battle.getWeather() == Weather.SNOW) {
            multiplier *= 1.5;
        }
        return this.defense * multiplier;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double calculateSpecialAttack() {
        double multiplier = ability.onCalculateSpecialAttack(this) * heldItem.onCalculateSpecialAttack(this);
        return this.specialAttack * getStageMultiplier(stageSpecialAttack) * multiplier;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public double calculateSpecialDefense(Battle battle) {
        double multiplier = ability.onCalculateSpecialDefense(this) * heldItem.onCalculateSpecialDefense(this);
        if (types.contains(Type.ROCK) && !battle.isWeatherSuppressed() && battle.getWeather() == Weather.SAND) {
            multiplier *= 1.5;
        }
        return this.specialDefense * getStageMultiplier(stageSpecialDefense) * multiplier;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public double calculateSpeed() {
        double multiplier = ability.onCalculateSpeed(this) * heldItem.onCalculateSpeed(this);
        if (this.getStatus() == Status.PARALYZE) {
            multiplier *= 0.5;
        }
        //Verify in which order these calculations should take place.
        return this.speed * getStageMultiplier(stageSpeed) * multiplier;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getStatApplyStage(StatEnum stat) {
        switch (stat) {
            case HP:
                return this.getCurrentHP();
            case ATK:
                return this.attack * getStageMultiplier(this.stageAttack);
            case DEF:
                return this.defense * getStageMultiplier(this.stageDefense);
            case SPATK:
                return this.specialAttack * getStageMultiplier(this.stageSpecialAttack);
            case SPDEF:
                return this.specialDefense * getStageMultiplier(this.stageSpecialDefense);
            case SPEED:
                return this.speed * getStageMultiplier(this.stageSpeed);
            default:
                throw new IllegalArgumentException("This method is not for accuracy or evasion");
        }
    }

    private double getStageMultiplier(int stage) {
        if (stage >= 0) {
            return 1 + (0.5 * stage);
        } else {
            return 2d / (2 + stage);
        }
    }

    public int getLevel() {
        return level;
    }

    public void takeDamage(int damage) {
        if (damage <= 0) {
            throw new IllegalArgumentException("Damage must be positive");
        }
        this.currentHP = currentHP - damage;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    public void heal(int health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Health restored must be positive");
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

    public int getStageAccuracy() {
        return stageAccuracy;
    }

    public void setStageAccuracy(int stageAccuracy) {
        this.stageAccuracy = stageAccuracy;
    }

    public int getStageEvasion() {
        return stageEvasion;
    }

    public void setStageEvasion(int stageEvasion) {
        this.stageEvasion = stageEvasion;
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

    public HeldItem getConsumedItem() {
        return consumedItem;
    }

    public void setConsumedItem(HeldItem consumedItem) {
        this.consumedItem = consumedItem;
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

    public Move getLastMoveUsed() {
        return lastMoveUsed;
    }

    public void setLastMoveUsed(Move lastMoveUsed) {
        this.lastMoveUsed = lastMoveUsed;
    }

    public boolean hasMovedThisTurn() {
        return movedThisTurn;
    }

    public void setMovedThisTurn(boolean movedThisTurn) {
        this.movedThisTurn = movedThisTurn;
    }

    public byte getHappiness() {
        return happiness;
    }

    public void setHappiness(byte happiness) {
        this.happiness = happiness;
    }

    public int getTurnsInBattle() {
        return turnsInBattle;
    }

    public void setTurnsInBattle(int turnsInBattle) {
        this.turnsInBattle = turnsInBattle;
    }

    public boolean isGrounded() {
        //This override Grounded status type is caused by Iron Ball, Ingrain, SmackDown, Thousand Arrows, and Gravity.
        if (hasVolatileStatus(GROUNDED) || hasVolatileStatus(ROOTED)) {
            return true;
        } else if (hasVolatileStatus(MAGNET_LEVITATION)) {
            return false;
        } else {
            return !(this.types.contains(Type.FLYING) || this.getHeldItem().equals(AIR_BALLOON) || (
                this.ability.equals(LEVITATE) && !hasVolatileStatus(ABILITY_IGNORED) && !hasVolatileStatus(
                    ABILITY_SUPPRESSED)));
        }
    }

    public boolean isTrapped(Battle battle) {
        List<Pokemon> opposingActivePokemon = battle.getOpposingActivePokemon(this);

        if (getHeldItem() == SHED_SHELL || getTypes().contains(Type.GHOST)) {
            return false;
        }

        for (Pokemon opponent : opposingActivePokemon) {
            if (battle.getPokemonWithinRange(opponent, opponent.getAbility().getRange()).contains(this)
                && ((opponent.getAbility() == MAGNET_PULL && this.getTypes().contains(Type.STEEL))
                || (opponent.getAbility() == ARENA_TRAP && this.isGrounded())
                || (opponent.getAbility() == SHADOW_TAG && this.getAbility() != SHADOW_TAG))) {
                return true;
            }
        }

        return hasVolatileStatus(CANT_ESCAPE) || hasVolatileStatus(NO_RETREAT) || hasVolatileStatus(OCTOLOCKED);
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

    public void decrementVolatileStatusTurns() {
        volatileStatuses.forEach((key, value) -> value.decrementTurns());
        volatileStatuses.entrySet()
            .removeIf(entry -> entry.getValue() != null && entry.getValue().getTurnsRemaining() == 0);
    }

    public boolean isAtFullHP() {
        return currentHP == maxHP;
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

    public void afterEntry(Battle battle) {
        setTurnsInBattle(0);
        //TODO: If Neutralizing Gas is active, give this mon the AbilitySuppressed volatile status.
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterEntry(this, battle);
        }
        heldItem.afterEntry(this, battle);
    }

    public double getMovePowerMultipliers(Pokemon opponent, Battle battle, Move move) {
        final double helpingHand;
        if (this.hasVolatileStatus(HELPING_HAND)) {
            helpingHand = ((HelpingHandStatus) this.getVolatileStatus(HELPING_HAND)).getLevels() * 1.5;
        } else {
            helpingHand = 1;
        }
        final double abilityMultiplier = (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) ? ability.getMovePowerMultipliers(
            this,
            opponent,
            battle,
            move) : 1;
        return abilityMultiplier * heldItem.getMovePowerMultipliers(
            this,
            opponent,
            battle,
            move) * helpingHand;
    }

    public double getAttackMultipliersFromAbilityAndItem(Pokemon opponent, Battle battle, Move move) {
        final double abilityMultiplier = (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility)
            ? ability.getAttackMultipliers(this, opponent, battle, move) : 1;
        if (abilityMultiplier == 0) {
            return 0;
        } else {
            return abilityMultiplier * heldItem.getAttackMultipliers(this, opponent, battle, move);
        }
        //Again, I don't want to consume an item if the ability's going to nullify the effect anyway.
    }

    public double getDefenseMultipliersFromAbilityAndItem(Pokemon opponent, Battle battle, Move move) {
        final double abilityMultiplier = (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility)
            ? ability.getDefenseMultipliers(this, opponent, battle, move) : 1;
        if (abilityMultiplier == 0) {
            return 0;
        } else {
            return abilityMultiplier * heldItem.getDefenseMultipliers(this, opponent, battle, move);
        }
        //Again, I don't want to consume an item if the ability's going to nullify the effect anyway.
    }

    public void beforeAttack(Pokemon target) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.beforeAttack(this, target);
        }
        heldItem.beforeAttack(this, target);
    }

    public void afterAttack(Pokemon opponent, Battle battle, Move move) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterAttack(this, opponent, battle, move);
        }
        heldItem.afterAttack(this, opponent, battle, move);
    }

    public boolean isDropPossible(StatEnum stat) {
        if (!(this.getAbility() instanceof IgnorableAbility && this.hasVolatileStatus(ABILITY_IGNORED))
            && (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility)) {
            return ability.isDropPossible(stat) && heldItem.isDropPossible(stat);
        } else {
            return heldItem.isDropPossible(stat);
        }
    }

    public void afterDrop(Pokemon opponent, Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterDrop(this, opponent, battle);
        }
        heldItem.afterDrop(this, opponent, battle);
    }

    public double beforeHit(Pokemon opponent, Battle battle, Move move) {
        double abilityMultiplier = 1;
        if (!(ability instanceof IgnorableAbility && opponent.getAbility() instanceof NullifyingAbility) && (
            !this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
                || this.getAbility() instanceof UnsuppressableAbility)) {
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
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterHit(this, opponent, battle, previousHP, move);
        }
        heldItem.afterHit(this, opponent, battle, previousHP, move);
    }

    public void afterStatus(Pokemon opponent, Battle battle) {
        //TODO: HeldItem is first here because Rawst Berry will activate before Water Veil. Is this how it works for all abilities/items?
        heldItem.afterStatus(this, opponent, battle);
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterStatus(this, opponent, battle);
        }
    }

    public void beforeTurn(Pokemon opponent, Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.beforeTurn(this, opponent, battle);
        }
        heldItem.beforeTurn(this, opponent, battle);
    }

    public void afterTurn(Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterTurn(this, battle);
        }
        heldItem.afterTurn(this, battle);
    }

    public void afterFaint(Pokemon opponent, Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterFaint(this, opponent, battle);
        }
        heldItem.afterFaint(this, opponent, battle);

        Pickup.removeFromConsumedItems(this);
    }

    public void afterKO(Pokemon opponent, Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.afterKO(this, opponent, battle);
        }
        heldItem.afterKO(this, opponent, battle);
    }

    public void beforeSwitchOut(Pokemon opponent, Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.beforeSwitchOut(this, opponent, battle);
        }
        heldItem.beforeSwitchOut(this, opponent, battle);

        Pickup.removeFromConsumedItems(this);
    }

    public void onWeatherChange(Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.onWeatherChange(this, battle);
        }
        heldItem.onWeatherChange(this, battle);
    }

    public void onTerrainChange(Battle battle) {
        if (!this.hasVolatileStatus(VolatileStatusType.ABILITY_SUPPRESSED)
            || this.getAbility() instanceof UnsuppressableAbility) {
            ability.onTerrainChange(this, battle);
        }
        heldItem.onTerrainChange(this, battle);
    }

    public double getWeight() {
        //Weight calculations are rounded to nearest tenth of a kilogram, which we can do by multiplying the weight by 10 and treating it as an integer.
        //This is why that CSV I found with the weight values had them all listed at 10x the values I was used to seeing.
        final int baseWeight = (int) (pokedexEntry.getWeight() * 10);
        final double floatStoneMultiplier = this.getHeldItem() == FLOAT_STONE ? .5 : 1;
        final int autotomizedLevels = hasVolatileStatus(AUTOTOMIZED)
            ? ((AutotomizedStatus) getVolatileStatus(AUTOTOMIZED)).getLevels()
            : 1;

        final int weightAfterAutomotize = Math.max(1, baseWeight - (1000 * autotomizedLevels));
        final int roundedWeight = (int) Math.max(
            1,
            weightAfterAutomotize * floatStoneMultiplier * ability.getWeightMultiplier());
        return roundedWeight / 10d;
    }
}
