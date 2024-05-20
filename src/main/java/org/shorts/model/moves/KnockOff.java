package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.items.PrimalOrb;
import org.shorts.model.items.ZCrystal;
import org.shorts.model.pokemon.Arceus;
import org.shorts.model.pokemon.Genesect;
import org.shorts.model.pokemon.Giratina;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Silvally;
import org.shorts.model.pokemon.Zacian;
import org.shorts.model.pokemon.Zamazenta;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class KnockOff extends PhysicalMove {

    static final double MULTIPLIER = 1.5;

    public KnockOff() {
        super("Knock Off", 65, 100, Type.DARK, 32, true, 100);
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
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasFainted() && bonusDamageApplies(attacker, defender) && !defender.hasVolatileStatus(SUBSTITUTE)
            && (defender.getAbility() != STICKY_HOLD || defender.hasFainted() || defender.hasVolatileStatus(
            ABILITY_IGNORED) || defender.hasVolatileStatus(ABILITY_SUPPRESSED))) {

            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setHeldItem(NO_ITEM);
    }

    private boolean bonusDamageApplies(Pokemon user, Pokemon target) {
        if (target.getHeldItem() == NO_ITEM) {
            return false;
        } else if ((target instanceof Giratina || user instanceof Giratina) && target.getHeldItem() == GRISEOUS_ORB) {
            return false;
        } else if ((target instanceof Arceus || user instanceof Arceus)
            && target.getHeldItem() instanceof PlateItem) {
            return false;
        } else if ((target instanceof Genesect || user instanceof Genesect)
            && target.getHeldItem() instanceof DriveItem) {
            return false;
        } else if ((target instanceof Silvally || user instanceof Silvally)
            && target.getHeldItem() instanceof MemoryItem) {
            return false;
        } else if ((target instanceof Zacian || user instanceof Zacian)
            && target.getHeldItem() == RUSTED_SWORD) {
            return false;
        } else if ((target instanceof Zamazenta || user instanceof Zamazenta)
            && target.getHeldItem() == RUSTED_SHIELD) {
            return false;
        } else if (target.getHeldItem() instanceof MegaStone) {
            MegaStone megaStone = (MegaStone) target.getHeldItem();
            return !(megaStone.isCorrectPokemon(target) || megaStone.isCorrectPokemon(user));
        } else if (target.getHeldItem() instanceof PrimalOrb) {
            PrimalOrb primalOrb = (PrimalOrb) target.getHeldItem();
            return !(primalOrb.isCorrectPokemon(target) || primalOrb.isCorrectPokemon(user));
        } else {
            return !(target.getHeldItem() instanceof ZCrystal); //Z-Crystal can't be knocked off. Otherwise, return true for everything else.
        }
    }
}
