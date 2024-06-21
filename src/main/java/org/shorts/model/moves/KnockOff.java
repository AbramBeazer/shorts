package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Protosynthesis;
import org.shorts.model.abilities.QuarkDrive;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.items.PrimalOrb;
import org.shorts.model.items.ZCrystal;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class KnockOff extends Move {

    static final double MULTIPLIER = 1.5;

    public KnockOff() {
        super("Knock Off", 65, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 32, true, 100);
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        if (bonusDamageApplies(user, target)) {
            return MULTIPLIER;
        } else {
            return 1;
        }
    }

    //TODO: Test "If the user faints due to the target's Ability (Rough Skin or Iron Barbs) or held Rocky Helmet, it cannot remove the target's held item. However, Knock Off will still remove the target's held item if the user faints due to its own held Life Orb."
    //      I need to verify when Life Orb damage happens. Does it happen after Rough Skin/Iron Barbs/Rocky Helmet damage?
    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (!user.hasFainted() && bonusDamageApplies(user, target) && !target.hasVolatileStatus(SUBSTITUTE)
            && (target.getAbility() != STICKY_HOLD || target.hasFainted() || target.hasVolatileStatus(
            ABILITY_IGNORED) || target.hasVolatileStatus(ABILITY_SUPPRESSED))) {

            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setHeldItem(NO_ITEM);
    }

    private boolean bonusDamageApplies(Pokemon user, Pokemon target) {
        //TODO: Remember to add check for Ogerpon's masks
        final PokedexEntry targetDex = target.getPokedexEntry();
        final PokedexEntry userDex = user.getPokedexEntry();
        if (target.getHeldItem() == NO_ITEM) {
            return false;
        } else if ((Pokedex.get("Giratina").equals(userDex) || Pokedex.get("Giratina").equals(targetDex))
            && target.getHeldItem() == GRISEOUS_ORB) {
            return false;
        } else if ((Pokedex.get("Arceus").equals(userDex) || Pokedex.get("Arceus").equals(targetDex))
            && target.getHeldItem() instanceof PlateItem) {
            return false;
        } else if ((Pokedex.get("Genesect").equals(userDex) || Pokedex.get("Genesect").equals(targetDex))
            && target.getHeldItem() instanceof DriveItem) {
            return false;
        } else if ((Pokedex.get("Silvally").equals(userDex) || Pokedex.get("Silvally").equals(targetDex))
            && target.getHeldItem() instanceof MemoryItem) {
            return false;
        } else if ((Pokedex.get("Zacian").equals(userDex) || Pokedex.get("Zacian").equals(targetDex))
            && target.getHeldItem() == RUSTED_SWORD) {
            return false;
        } else if ((Pokedex.get("Zamazenta").equals(userDex) || Pokedex.get("Zamazenta").equals(targetDex))
            && target.getHeldItem() == RUSTED_SHIELD) {
            return false;
        } else if (target.getHeldItem() instanceof MegaStone) {
            MegaStone megaStone = (MegaStone) target.getHeldItem();
            return !(megaStone.isCorrectPokemon(target) || megaStone.isCorrectPokemon(user));
        } else if (target.getHeldItem() instanceof PrimalOrb) {
            PrimalOrb primalOrb = (PrimalOrb) target.getHeldItem();
            return !(primalOrb.isCorrectPokemon(target) || primalOrb.isCorrectPokemon(user));
        } else if (target.getHeldItem() == BOOSTER_ENERGY && (user.getAbility() instanceof Protosynthesis
            || target.getAbility() instanceof Protosynthesis || user.getAbility() instanceof QuarkDrive
            || target.getAbility() instanceof QuarkDrive)) {
            return false;
        } else {
            return !(target.getHeldItem() instanceof ZCrystal); //Z-Crystal can't be knocked off. Otherwise, return true for everything else.
        }
    }
}
