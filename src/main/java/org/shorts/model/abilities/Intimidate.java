package org.shorts.model.abilities;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.GuardDog.*;
import static org.shorts.model.abilities.Rattled.*;
import static org.shorts.model.abilities.Scrappy.*;
import static org.shorts.model.abilities.StatusImmuneAbility.*;
import static org.shorts.model.items.AdrenalineOrb.*;
import static org.shorts.model.items.NoItem.*;

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
            if (!opponent.isBehindSub() && opponent.isDropPossible(StatEnum.ATK)
                && opponent.getAbility() != OWN_TEMPO && opponent.getAbility() != OBLIVIOUS
                && opponent.getAbility() != INNER_FOCUS && opponent.getAbility() != SCRAPPY) {

                if (opponent.getAbility() == GUARD_DOG) {
                    opponent.changeStat(battle, opponent, 1, StatEnum.ATK);
                } else {
                    opponent.changeStat(battle, self, -1, StatEnum.ATK);
                }

                if (opponent.canChangeStat(1, StatEnum.SPEED)) {
                    if (opponent.getAbility() == RATTLED) {
                        opponent.changeStat(battle, opponent, 1, StatEnum.SPEED);
                    }
                    //TODO: Does Rattled stack with Adrenaline Orb here?
                    if (opponent.getHeldItem() == ADRENALINE_ORB) {

                        opponent.changeStat(battle, opponent, 1, StatEnum.SPEED);
                        opponent.setConsumedItem(opponent.getHeldItem());
                        opponent.setHeldItem(NO_ITEM);
                        Pickup.addToConsumedItems(opponent);
                    }
                }
            }
        }
        //TODO: If multiple Pokémon each holding an Adrenaline Orb are affected by Intimidate, each Pokémon will be affected by Intimidate and consume their Adrenaline Orb before Symbiosis transfers any item (including Adrenaline Orbs).
        //  Do the Symbiosis logic here.
    }
}
