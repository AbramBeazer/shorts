package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Giratina;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class KnockOff extends PhysicalMove {

    static final double MULTIPLIER = 1.5;

    public KnockOff() {
        super("Knock Off", 65, 100, Type.DARK, 32, true, 100);
    }

    @Override
    protected double calculateMovePower(Pokemon user, Pokemon target, Battle battle) {
        if (itemCanBeKnockedOff(target)) {
            return 1.5;
        } else {
            return 1;
        }
    }

    @Override
    public void trySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        if (!attacker.hasFainted() && itemCanBeKnockedOff(defender) && !defender.hasVolatileStatus(SUBSTITUTE)) {
            //TODO: Test "If the user faints due to the target's Ability (Rough Skin or Iron Barbs) or held Rocky Helmet, it cannot remove the target's held item. However, Knock Off will still remove the target's held item if the user faints due to its own held Life Orb."
            //      I need to verify when Life Orb damage happens. Does it happen after Rough Skin/Iron Barbs/Rocky Helmet damage?
            super.trySecondaryEffect(attacker, defender, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon attacker, Pokemon defender, Battle battle) {
        super.applySecondaryEffect(attacker, defender, battle);
    }

    private boolean itemCanBeKnockedOff(Pokemon target) {
        if (target.getAbility() == STICKY_HOLD && !target.hasVolatileStatus(VolatileStatusType.ABILITY_IGNORED)) {
            return false;
        } else if (target instanceof Giratina && target.getHeldItem() == GRISEOUS_ORB) {
            return false;
        } else if (target instanceof Arceus && target.getHeldItem() instanceof PlateItem) {
            return false;
        } else if (target instanceof Genesect && target.getHeldItem() instanceof DriveItem) {
            return false;
        } else if (target.getHeldItem() instanceof ZCrystal)

    }
}
