package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Protosynthesis;
import org.shorts.model.abilities.QuarkDrive;
import org.shorts.model.items.DriveItem;
import org.shorts.model.items.HeldItem;
import org.shorts.model.items.MegaStone;
import org.shorts.model.items.MemoryItem;
import org.shorts.model.items.PlateItem;
import org.shorts.model.items.berries.Berry;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.PokedexEntry;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.StatusType;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Klutz.KLUTZ;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.FlameOrb.FLAME_ORB;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.KingsRock.KINGS_ROCK;
import static org.shorts.model.items.LightBall.LIGHT_BALL;
import static org.shorts.model.items.MentalHerb.MENTAL_HERB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.PoisonBarb.POISON_BARB;
import static org.shorts.model.items.RazorFang.RAZOR_FANG;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.items.ToxicOrb.TOXIC_ORB;
import static org.shorts.model.items.WhiteHerb.WHITE_HERB;

public class Fling extends Move {

    public Fling() {
        super("Fling", 0, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 100);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        return user.getHeldItem().getFlingPower();
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        final PokedexEntry entry = user.getPokedexEntry();
        //TODO: Is the item consumed if it misses?
        final HeldItem item = user.getHeldItem();
        if (!(item == NO_ITEM || getPower(user, target, battle) == 0 || battle.getMagicRoomTurns() > 0
            || user.getAbility() == KLUTZ || user.hasVolatileStatus(VolatileStatusType.EMBARGOED)
            || (item == BOOSTER_ENERGY
            && (user.getAbility() instanceof Protosynthesis || user.getAbility() instanceof QuarkDrive))
            || (entry.equalsSpecies(Pokedex.get("Giratina")) && item == GRISEOUS_ORB)
            || (entry.equalsSpecies(Pokedex.get("Arceus")) && item instanceof PlateItem)
            || (entry.equalsSpecies(Pokedex.get("Genesect")) && item instanceof DriveItem)
            || (entry.equalsSpecies(Pokedex.get("Silvally")) && item instanceof MemoryItem)
            || (entry.equalsSpecies(Pokedex.get("Zacian")) && item == RUSTED_SWORD)
            || (entry.equalsSpecies(Pokedex.get("Zamazenta")) && item == RUSTED_SHIELD)
            || (item instanceof MegaStone && ((MegaStone) item).isCorrectPokemon(user)))) {
            super.executeOnTarget(user, target, battle);
        }
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should this have a secondary effect if it hits the target's substitute?
        super.trySecondaryEffect(user, target, battle);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final HeldItem item = user.getHeldItem();
        if (item instanceof Berry) {
            if (((Berry) item).tryEatingBerry(target, battle)) {
                target.setConsumedItem(item); //TODO: Does this become the consumed item for both the flinger and the target?
            }
        } else {
            user.setConsumedItem(item);
        }

        user.setHeldItem(NO_ITEM);

        if (item == FLAME_ORB && StatusType.BURN.isStatusPossible(target, battle)) {
            target.setStatus(Status.BURN);
        } else if (item == LIGHT_BALL && StatusType.PARALYZE.isStatusPossible(target, battle)) {
            target.setStatus(Status.PARALYZE);
        } else if (item == TOXIC_ORB && StatusType.TOXIC_POISON.isStatusPossible(target, battle)) {
            target.setStatus(Status.TOXIC_POISON);
        } else if (item == POISON_BARB && StatusType.POISON.isStatusPossible(target, battle)) {
            target.setStatus(Status.POISON);
        } else if ((item == RAZOR_FANG || item == KINGS_ROCK) && VolatileStatusType.FLINCH.isStatusPossible(
            target,
            battle)) {
            target.addVolatileStatus(new VolatileStatus(VolatileStatusType.FLINCH, 1));
        } else if (item == MENTAL_HERB) {
            item.onGainItem(target, battle);
        } else if (item == WHITE_HERB) {
            item.onGainItem(target, battle);
        }
    }
}
