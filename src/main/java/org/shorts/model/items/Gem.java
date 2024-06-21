package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.types.Type.BUG;
import static org.shorts.model.types.Type.DARK;
import static org.shorts.model.types.Type.DRAGON;
import static org.shorts.model.types.Type.ELECTRIC;
import static org.shorts.model.types.Type.FAIRY;
import static org.shorts.model.types.Type.FIGHTING;
import static org.shorts.model.types.Type.FIRE;
import static org.shorts.model.types.Type.FLYING;
import static org.shorts.model.types.Type.GHOST;
import static org.shorts.model.types.Type.GRASS;
import static org.shorts.model.types.Type.GROUND;
import static org.shorts.model.types.Type.ICE;
import static org.shorts.model.types.Type.NORMAL;
import static org.shorts.model.types.Type.POISON;
import static org.shorts.model.types.Type.PSYCHIC;
import static org.shorts.model.types.Type.ROCK;
import static org.shorts.model.types.Type.STEEL;
import static org.shorts.model.types.Type.WATER;

public class Gem extends HeldItem {

    private Type type;

    private Gem(Type type) {
        super(type.getName() + " Gem", 0);
    }

    @Override
    public double getMovePowerMultipliers(Pokemon user, Pokemon opponent, Battle battle, Move move) {
        //TODO:
        //        The Gem will not be consumed if the attack misses or fails. Bide, Focus Punch, and damaging moves with a charging turn consume the Gem at the first stage of the move, before dealing damage.
        //
        //        If the type of a move is changed with Normalize, Pixilate, Refrigerate, Aerilate, Ion Deluge, or Electrify, the Gem must match the type of the move after it has been modified. Moves that call other moves will only consume a Gem if the called move is of the appropriate type for the Gem. Hidden Power and Weather Ball can consume Gems, but only if the type of the Gem matches the type of damage that will be dealt.
        //
        //        Judgment and Techno Blast can consume Normal Gems, but will be Normal-type for that execution even if the Pokémon is given a Plate or Drive by Symbiosis upon consuming the Gem. If a Pokémon consumes a Gem for an attack and is immediately given another of the same Gem by Symbiosis, that Gem will not be consumed for that attack; if a Pokémon consumes a Power Herb using a damaging move and is immediately given a Gem of the appropriate type, that Gem will be consumed for that attack.
        //
        //        Fixed-damage moves (such as Dragon Rage, Counter, and Endeavor) will consume Gems but will not have their damage modified. Present consumes a Gem even if it heals the target, but the amount healed is unaffected. Fling fails if the user is holding a Gem. OHKO moves, Struggle, Grass Pledge, Water Pledge, and Fire Pledge will never consume a Gem.
        if (move.getType() == this.type) {
            user.setConsumedItem(this);
            user.setHeldItem(NO_ITEM);
            return 1.3;
        } else {
            return 1;
        }
    }

    public static final Gem FIRE_GEM = new Gem(FIRE);
    public static final Gem WATER_GEM = new Gem(WATER);
    public static final Gem ELECTRIC_GEM = new Gem(ELECTRIC);
    public static final Gem GRASS_GEM = new Gem(GRASS);
    public static final Gem ICE_GEM = new Gem(ICE);
    public static final Gem FIGHTING_GEM = new Gem(FIGHTING);
    public static final Gem POISON_GEM = new Gem(POISON);
    public static final Gem GROUND_GEM = new Gem(GROUND);
    public static final Gem FLYING_GEM = new Gem(FLYING);
    public static final Gem PSYCHIC_GEM = new Gem(PSYCHIC);
    public static final Gem BUG_GEM = new Gem(BUG);
    public static final Gem ROCK_GEM = new Gem(ROCK);
    public static final Gem GHOST_GEM = new Gem(GHOST);
    public static final Gem DRAGON_GEM = new Gem(DRAGON);
    public static final Gem DARK_GEM = new Gem(DARK);
    public static final Gem STEEL_GEM = new Gem(STEEL);
    public static final Gem NORMAL_GEM = new Gem(NORMAL);
    public static final Gem FAIRY_GEM = new Gem(FAIRY);
}
