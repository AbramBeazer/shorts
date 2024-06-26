package org.shorts.model.types;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Type {

    public static final double NEUTRAL = 1;
    public static final double STAB = 1.5;
    public static final double SUPER_EFFECTIVE = 2;

    public static final double QUAD_EFFECTIVE = 4;
    public static final double OCTO_EFFECTIVE = 8;
    public static final double NOT_VERY_EFFECTIVE = 0.5;

    public static final double QUAD_RESIST = 0.25;
    public static final double OCTO_RESIST = 0.125;
    public static final double IMMUNE = 0;

    private final TypeId id;
    private final String name;
    private final List<TypeId> weaknesses;
    private final List<TypeId> resistances;
    private final List<TypeId> immunities;

    public TypeId getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<TypeId> getWeaknesses() {
        return weaknesses;
    }

    public List<TypeId> getResistances() {
        return resistances;
    }

    public List<TypeId> getImmunities() {
        return immunities;
    }

    private Type(TypeId id, String name, List<TypeId> weaknesses, List<TypeId> resistances, List<TypeId> immunities) {
        this.id = Objects.requireNonNull(id, "ID of Type cannot be null");
        this.name = Objects.requireNonNull(name, "Name of Type cannot be null");
        this.weaknesses = Objects.requireNonNull(weaknesses, "Type's weaknesses cannot be null");
        this.resistances = Objects.requireNonNull(resistances, "Type's resistances cannot be null");
        this.immunities = Objects.requireNonNull(immunities, "Type's immunities cannot be null");
    }

    public static double getTypeMultiplier(Type moveType, Set<Type> defenderTypes) throws TooManyTypesException {
        if (defenderTypes.size() > 2) {
            throw new TooManyTypesException(defenderTypes);
        }
        double multiplier = 1;
        for (Type def : defenderTypes) {
            if (def.getWeaknesses().contains(moveType.getId())) {
                multiplier *= SUPER_EFFECTIVE;
            } else if (def.getResistances().contains(moveType.getId())) {
                multiplier *= NOT_VERY_EFFECTIVE;
            } else if (def.getImmunities().contains(moveType.getId())) {
                multiplier *= IMMUNE;
            }
        }
        return multiplier;
    }

    public static double getSTABMultiplier(Type moveType, Set<Type> attackerTypes) throws TooManyTypesException {
        if (attackerTypes.size() > 2) {
            throw new TooManyTypesException(attackerTypes);
        }
        return attackerTypes.contains(moveType) ? STAB : 1;
    }

    public static final Type NORMAL = new Type(
        TypeId.NORMAL,
        "Normal",
        List.of(TypeId.FIGHTING),
        List.of(),
        List.of(TypeId.GHOST));

    public static final Type FIRE = new Type(
        TypeId.FIRE,
        "Fire",
        List.of(TypeId.WATER, TypeId.GROUND, TypeId.ROCK),
        List.of(TypeId.FIRE, TypeId.GRASS, TypeId.ICE, TypeId.BUG, TypeId.STEEL, TypeId.FAIRY),
        List.of());

    public static final Type WATER = new Type(
        TypeId.WATER,
        "Water",
        List.of(TypeId.GRASS, TypeId.ELECTRIC),
        List.of(TypeId.WATER, TypeId.FIRE, TypeId.ICE, TypeId.STEEL),
        List.of());

    public static final Type GRASS = new Type(
        TypeId.GRASS,
        "Grass",
        List.of(TypeId.FIRE, TypeId.ICE, TypeId.POISON, TypeId.FLYING, TypeId.BUG),
        List.of(TypeId.WATER, TypeId.GRASS, TypeId.ELECTRIC, TypeId.GROUND),
        List.of());

    public static final Type ELECTRIC = new Type(
        TypeId.ELECTRIC,
        "Electric",
        List.of(TypeId.GROUND),
        List.of(TypeId.ELECTRIC, TypeId.FLYING, TypeId.STEEL),
        List.of());

    public static final Type ICE = new Type(
        TypeId.ICE,
        "Ice",
        List.of(TypeId.FIRE, TypeId.FIGHTING, TypeId.ROCK, TypeId.STEEL),
        List.of(TypeId.ICE),
        List.of());

    public static final Type FIGHTING = new Type(
        TypeId.FIGHTING,
        "Fighting",
        List.of(TypeId.PSYCHIC, TypeId.FLYING, TypeId.FAIRY),
        List.of(TypeId.ROCK, TypeId.BUG, TypeId.DARK),
        List.of());

    public static final Type POISON = new Type(
        TypeId.POISON,
        "Poison",
        List.of(TypeId.GROUND, TypeId.PSYCHIC),
        List.of(TypeId.GRASS, TypeId.FIGHTING, TypeId.POISON, TypeId.BUG, TypeId.FAIRY),
        List.of());

    public static final Type GROUND = new Type(
        TypeId.GROUND,
        "Ground",
        List.of(TypeId.WATER, TypeId.GRASS, TypeId.ICE),
        List.of(TypeId.POISON, TypeId.ROCK),
        List.of(TypeId.ELECTRIC));

    public static final Type FLYING = new Type(
        TypeId.FLYING,
        "Flying",
        List.of(TypeId.ELECTRIC, TypeId.ROCK, TypeId.ICE),
        List.of(TypeId.GRASS, TypeId.FIGHTING, TypeId.BUG),
        List.of(TypeId.GROUND));

    public static final Type PSYCHIC = new Type(
        TypeId.PSYCHIC,
        "Psychic",
        List.of(TypeId.BUG, TypeId.GHOST, TypeId.DARK),
        List.of(TypeId.FIGHTING, TypeId.PSYCHIC),
        List.of());

    public static final Type BUG = new Type(
        TypeId.BUG,
        "Bug",
        List.of(TypeId.FIRE, TypeId.FLYING, TypeId.ROCK),
        List.of(TypeId.GRASS, TypeId.FIGHTING, TypeId.GROUND),
        List.of());

    public static final Type ROCK = new Type(
        TypeId.ROCK,
        "Rock",
        List.of(TypeId.WATER, TypeId.GRASS, TypeId.FIGHTING, TypeId.GROUND, TypeId.STEEL),
        List.of(TypeId.NORMAL, TypeId.FIRE, TypeId.POISON, TypeId.FLYING),
        List.of());

    public static final Type GHOST = new Type(
        TypeId.GHOST,
        "Ghost",
        List.of(TypeId.GHOST, TypeId.DARK),
        List.of(TypeId.POISON, TypeId.BUG),
        List.of(TypeId.NORMAL, TypeId.FIGHTING));

    public static final Type DRAGON = new Type(
        TypeId.DRAGON,
        "Dragon",
        List.of(TypeId.ICE, TypeId.DRAGON, TypeId.FAIRY),
        List.of(TypeId.FIRE, TypeId.WATER, TypeId.GRASS, TypeId.ELECTRIC),
        List.of());

    public static final Type DARK = new Type(
        TypeId.DARK,
        "Dark",
        List.of(TypeId.FIGHTING, TypeId.BUG, TypeId.FAIRY),
        List.of(TypeId.GHOST, TypeId.DARK),
        List.of(TypeId.PSYCHIC));

    public static final Type STEEL = new Type(
        TypeId.STEEL,
        "Steel",
        List.of(TypeId.FIRE, TypeId.FIGHTING, TypeId.GROUND),
        List.of(
            TypeId.NORMAL,
            TypeId.GRASS,
            TypeId.ICE,
            TypeId.FLYING,
            TypeId.PSYCHIC,
            TypeId.BUG,
            TypeId.ROCK,
            TypeId.DRAGON,
            TypeId.STEEL,
            TypeId.FAIRY),
        List.of(TypeId.POISON));

    public static final Type FAIRY = new Type(
        TypeId.FAIRY,
        "Fairy",
        List.of(TypeId.POISON, TypeId.STEEL),
        List.of(TypeId.FIGHTING, TypeId.BUG, TypeId.DARK),
        List.of(TypeId.DRAGON));

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            Type t = (Type) obj;
            return t.id.equals(this.id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 17 * this.resistances.hashCode() * this.weaknesses.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    private static final Map<String, Type> STRING_TYPE_MAP;

    static {
        Map<String, Type> map = new ConcurrentHashMap<>();
        map.put(NORMAL.name, NORMAL);
        map.put(FLYING.name, FLYING);
        map.put(ROCK.name, ROCK);
        map.put(GROUND.name, GROUND);
        map.put(STEEL.name, STEEL);
        map.put(BUG.name, BUG);
        map.put(POISON.name, POISON);
        map.put(FIGHTING.name, FIGHTING);
        map.put(GHOST.name, GHOST);
        map.put(DARK.name, DARK);
        map.put(PSYCHIC.name, PSYCHIC);
        map.put(DRAGON.name, DRAGON);
        map.put(FAIRY.name, FAIRY);
        map.put(FIRE.name, FIRE);
        map.put(WATER.name, WATER);
        map.put(GRASS.name, GRASS);
        map.put(ELECTRIC.name, ELECTRIC);
        map.put(ICE.name, ICE);

        STRING_TYPE_MAP = Collections.unmodifiableMap(map);
    }

    public static Type fromString(String t) {
        return STRING_TYPE_MAP.get(t);
    }

    private enum TypeId {
        NORMAL,
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        ICE,
        FIGHTING,
        POISON,
        GROUND,
        FLYING,
        PSYCHIC,
        BUG,
        ROCK,
        GHOST,
        DRAGON,
        DARK,
        STEEL,
        FAIRY
    }
}
