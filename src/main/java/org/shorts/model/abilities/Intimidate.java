package org.shorts.model.abilities;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.GuardDog.GUARD_DOG;
import static org.shorts.model.abilities.Rattled.RATTLED;
import static org.shorts.model.abilities.Scrappy.SCRAPPY;
import static org.shorts.model.abilities.StatusImmuneAbility.INNER_FOCUS;
import static org.shorts.model.abilities.StatusImmuneAbility.OBLIVIOUS;
import static org.shorts.model.abilities.StatusImmuneAbility.OWN_TEMPO;
import static org.shorts.model.items.AdrenalineOrb.ADRENALINE_ORB;
import static org.shorts.model.items.NoItem.NO_ITEM;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class Intimidate extends Ability {

    private Intimidate() {
        super("Intimidate", Range.ALL_ADJACENT_OPPONENTS);
    }

    public static final Intimidate INTIMIDATE = new Intimidate();

    @Override
    public void afterEntry(Pokemon self, Battle battle) {
        tryEffect(self, battle);
    }

    @Override
    public void onGainAbility(Pokemon self, Battle battle) {
        tryEffect(self, battle);
    }

    protected void tryEffect(Pokemon self, Battle battle) {
        List<Pokemon> targets = battle.getPokemonWithinRange(self, this.getRange());
        for (Pokemon opponent : targets) {
            if (!opponent.hasVolatileStatus(SUBSTITUTE) && opponent.isDropPossible(StatEnum.ATK)
                && opponent.getAbility() != OWN_TEMPO && opponent.getAbility() != OBLIVIOUS
                && opponent.getAbility() != INNER_FOCUS && opponent.getAbility() != SCRAPPY) {

                if (opponent.getAbility() == GUARD_DOG) {
                    opponent.changeStat(1, StatEnum.ATK);
                } else {
                    opponent.changeStat(-1, StatEnum.ATK);
                    opponent.afterDrop(self, battle);
                }

                if (opponent.canChangeStat(1, StatEnum.SPEED)) {
                    if (opponent.getAbility() == RATTLED) {
                        opponent.changeStat(1, StatEnum.SPEED);
                    }
                    //TODO: Does Rattled stack with Adrenaline Orb here?
                    if (opponent.getHeldItem() == ADRENALINE_ORB) {

                        opponent.changeStat(1, StatEnum.SPEED);
                        opponent.setConsumedItem(opponent.getHeldItem());
                        opponent.setHeldItem(NO_ITEM);
                    }
                }
            }
        }
        //TODO: If multiple Pokémon each holding an Adrenaline Orb are affected by Intimidate, each Pokémon will be affected by Intimidate and consume their Adrenaline Orb before Symbiosis transfers any item (including Adrenaline Orbs).
        //  Do the Symbiosis logic here.
    }
}
