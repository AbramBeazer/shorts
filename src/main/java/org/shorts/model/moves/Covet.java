package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Protosynthesis;
import org.shorts.model.abilities.QuarkDrive;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.items.PrimalOrb;
import org.shorts.model.items.RustedShield;
import org.shorts.model.items.RustedSword;
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
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.status.VolatileStatusType.ABILITY_IGNORED;
import static org.shorts.model.status.VolatileStatusType.ABILITY_SUPPRESSED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class Covet extends Move {

    public Covet() {
        super("Covet", 60, 100, Type.NORMAL, Category.PHYSICAL, Range.NORMAL, 40, true, 100);
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        if (user.getHeldItem() == NO_ITEM && target.getHeldItem() != NO_ITEM && !target.hasVolatileStatus(SUBSTITUTE)
            && !user.hasFainted() && (target.getAbility() != STICKY_HOLD || target.hasFainted()
            || target.hasVolatileStatus(ABILITY_SUPPRESSED) || target.hasVolatileStatus(ABILITY_IGNORED))
            && !(target.getHeldItem() == GRISEOUS_ORB && (user instanceof Giratina || target instanceof Giratina))
            && !(target.getHeldItem() instanceof PlateItem && (user instanceof Arceus || target instanceof Arceus))
            && !(target.getHeldItem() instanceof DriveItem && (user instanceof Genesect || target instanceof Genesect))
            && !(target.getHeldItem() instanceof MemoryItem && (user instanceof Silvally || target instanceof Silvally))
            && !(target.getHeldItem() instanceof RustedSword && (user instanceof Zacian || target instanceof Zacian))
            && !(target.getHeldItem() instanceof RustedShield && (user instanceof Zamazenta
            || target instanceof Zamazenta)) && !(target.getHeldItem() instanceof ZCrystal)
            && !(target.getHeldItem() instanceof MegaStone && (((MegaStone) target.getHeldItem()).isCorrectPokemon(user)
            || ((MegaStone) target.getHeldItem()).isCorrectPokemon(target)))
            && !(target.getHeldItem() instanceof PrimalOrb && (((PrimalOrb) target.getHeldItem()).isCorrectPokemon(user)
            || ((PrimalOrb) target.getHeldItem()).isCorrectPokemon(target)))
            && !(target.getHeldItem() == BOOSTER_ENERGY && (user.getAbility() instanceof Protosynthesis
            || target.getAbility() instanceof Protosynthesis || user.getAbility() instanceof QuarkDrive
            || target.getAbility() instanceof QuarkDrive))) {
            //TODO: Remember to add check for Ogerpon's masks
            //TODO: Does a Cell Battery or Snowball trigger before being stolen if Covet's type is converted? Also, does Covet fail to steal Booster Energy even if held by Koraidon or Miraidon?
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        user.setHeldItem(target.getHeldItem());
        target.setHeldItem(NO_ITEM);
    }
}