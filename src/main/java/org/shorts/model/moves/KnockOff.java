package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.PlateItem;
import org.shorts.model.items.PrimalOrb;
import org.shorts.model.items.ZCrystal;
import org.shorts.model.pokemon.Arceus;
import org.shorts.model.pokemon.Genesect;
import org.shorts.model.pokemon.Giratina;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class KnockOff extends PhysicalMove {

    static final double MULTIPLIER = 1.5;

    public KnockOff() {
        super("Knock Off", 65, 100, Type.DARK, 32, true, 100);
    }

    @Override
    protected double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
        if (itemCanBeKnockedOff(user, target)) {
            return MULTIPLIER;
        } else {
            return 1;
        }
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasFainted() && itemCanBeKnockedOff(attacker, defender)
            && !defender.hasVolatileStatus(SUBSTITUTE)) {
            //TODO: Test "If the user faints due to the target's Ability (Rough Skin or Iron Barbs) or held Rocky Helmet, it cannot remove the target's held item. However, Knock Off will still remove the target's held item if the user faints due to its own held Life Orb."
            //      I need to verify when Life Orb damage happens. Does it happen after Rough Skin/Iron Barbs/Rocky Helmet damage?
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        defender.setHeldItem(NO_ITEM);
    }

    private boolean itemCanBeKnockedOff(Pokemon user, Pokemon target) {
        if (target.getAbility() == STICKY_HOLD && !target.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)) {
            return false;
        } else if ((target instanceof Giratina || user instanceof Giratina) && target.getHeldItem() == GRISEOUS_ORB) {
            return false;
        } else if ((target instanceof Arceus || user instanceof Arceus) && target.getHeldItem() instanceof PlateItem) {
            return false;
        } else if ((target instanceof Genesect || user instanceof Genesect)
            && target.getHeldItem() instanceof DriveItem) {
            return false;
        } else if (target.getHeldItem() instanceof MegaStone && ((MegaStone) target.getHeldItem()).isCorrectPokemon(
            target)) {
            return false;
        } else if (target.getHeldItem() instanceof PrimalOrb && ((PrimalOrb) target.getHeldItem()).isCorrectPokemon(
            target)) {
            return false;
        } else {
            return !(target.getHeldItem() instanceof ZCrystal); //Z-Crystal can't be knocked off. Otherwise, return true for everything else.
        }
    }
}
