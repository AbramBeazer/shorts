package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.Mask;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.StickyHold.STICKY_HOLD;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class CorrosiveGas extends Move implements AffectedByMagicBounce {

    public CorrosiveGas() {
        super("Corrosive Gas", 0, 100, Type.POISON, Category.STATUS, Range.ALL_ADJACENT, 64, false, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final PokedexEntry entry = target.getPokedexEntry();
        final HeldItem item = target.getHeldItem();
        if (!target.hasVolatileStatus(SUBSTITUTE) && (target.getAbility() != STICKY_HOLD || target.hasVolatileStatus(
            ABILITY_SUPPRESSED) || target.hasVolatileStatus(ABILITY_IGNORED)) && !(
            entry.equalsSpecies(Pokedex.get("Giratina")) && item == GRISEOUS_ORB) && !(
            entry.equalsSpecies(Pokedex.get("Arceus")) && item instanceof PlateItem) && !(
            entry.equalsSpecies(Pokedex.get("Genesect")) && item instanceof DriveItem) && !(
            entry.equalsSpecies(Pokedex.get("Silvally")) && item instanceof MemoryItem) && !(
            entry.equalsSpecies(Pokedex.get("Zacian")) && item == RUSTED_SWORD) && !(
            entry.equalsSpecies(Pokedex.get("Zamazenta")) && item == RUSTED_SHIELD) && !(
            entry.equalsSpecies(Pokedex.get("Giratina")) && item instanceof Mask)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        target.setHeldItem(NO_ITEM);
    }
}
