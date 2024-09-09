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
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;
import org.shorts.model.types.Type;

import static org.shorts.Main.DECIMAL;
import static org.shorts.model.abilities.Klutz.KLUTZ;
import static org.shorts.model.items.BoosterEnergy.BOOSTER_ENERGY;
import static org.shorts.model.items.FlameOrb.FLAME_ORB;
import static org.shorts.model.items.GriseousOrb.GRISEOUS_ORB;
import static org.shorts.model.items.KingsRock.KINGS_ROCK;
import static org.shorts.model.items.LightBall.LIGHT_BALL;
import static org.shorts.model.items.MentalHerb.MENTAL_HERB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.items.RazorFang.RAZOR_FANG;
import static org.shorts.model.items.RustedShield.RUSTED_SHIELD;
import static org.shorts.model.items.RustedSword.RUSTED_SWORD;
import static org.shorts.model.items.ToxicOrb.TOXIC_ORB;
import static org.shorts.model.items.TypeBoostItem.POISON_BARB;
import static org.shorts.model.items.WhiteHerb.WHITE_HERB;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

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
        final HeldItem item = user.getHeldItem();

        if (!(item == NO_ITEM || getPower(user, target, battle) == 0 || battle.getMagicRoomTurns() > 0
            || user.getAbility() == KLUTZ || user.hasVolatileStatus(VolatileStatusType.EMBARGOED) || (
            item == BOOSTER_ENERGY && (user.getAbility() instanceof Protosynthesis
                || user.getAbility() instanceof QuarkDrive)) || (entry.equalsSpecies(Pokedex.get("Giratina"))
            && item == GRISEOUS_ORB) || (entry.equalsSpecies(Pokedex.get("Arceus")) && item instanceof PlateItem) || (
            entry.equalsSpecies(Pokedex.get("Genesect")) && item instanceof DriveItem) || (
            entry.equalsSpecies(Pokedex.get("Silvally")) && item instanceof MemoryItem) || (
            entry.equalsSpecies(Pokedex.get("Zacian")) && item == RUSTED_SWORD) || (
            entry.equalsSpecies(Pokedex.get("Zamazenta")) && item == RUSTED_SHIELD) || (item instanceof MegaStone
            && ((MegaStone) item).isCorrectPokemon(user)))) {

            if (rollToHit(user, target, battle)) {

                if (!isTargetProtected(user, target, battle)) {
                    final int previousTargetHP = target.getCurrentHP();

                    int damage = calculateDamage(user, target, battle);

                    final boolean hitSub = checkForHitSub(user, target);
                    if (hitSub) {
                        //TODO: Handle moves and abilities that ignore substitute.
                        ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).takeDamage(damage);
                    } else if (damage > 0) {
                        target.takeDamage(damage);
                        System.out.println(
                            target.getDisplayName() + " took " + DECIMAL.format(100d * damage / target.getMaxHP())
                                + "% (" + damage + ")");
                    }

                    if (!hitSub) {
                        target.afterHit(user, battle, previousTargetHP, this);
                    }

                    this.trySecondaryEffect(user, target, battle);

                    if (target.hasVolatileStatus(SUBSTITUTE)
                        && ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).getSubHP() == 0) {
                        target.removeVolatileStatus(SUBSTITUTE);
                    }

                }
                user.setHeldItem(NO_ITEM);
                user.setConsumedItem(item);

                if (!user.hasFainted()) {
                    user.afterAttack(target, battle, this);
                }
            }
        }
    }

    @Override
    public void trySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should the target consume the flung item if the sub took the attack?
        if (!target.hasVolatileStatus(SUBSTITUTE)) {
            super.trySecondaryEffect(user, target, battle);
        }
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        final HeldItem item = user.getHeldItem();

        if (!isTargetProtected(user, target, battle)) {
            if (item instanceof Berry) {
                ((Berry) item).tryEatingOtherBerry(target);
                //TODO: Does this become the consumed item for the both the user and target?
            } else if (item == FLAME_ORB && StatusType.BURN.isStatusPossible(target, battle)) {
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
}
