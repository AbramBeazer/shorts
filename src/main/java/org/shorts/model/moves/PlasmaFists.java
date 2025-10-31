package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class PlasmaFists extends Move implements PunchingMove {

    public PlasmaFists() {
        super("Plasma Fists", 100, 100, Type.ELECTRIC, Category.PHYSICAL, Range.NORMAL, 24, true, 100);
    }

    @Override
    protected void applySecondaryEffect(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Implement:
        //        In the remainder of the current turn, all Normal-type moves will become Electric-type, including status moves.
        //        This includes Max Moves, which will turn into Max Lightning (or G-Max Volt Crash or G-Max Stun Shock for Gigantamax Pikachu and Gigantamax Toxtricity, respectively).
        //        The type-changing effect is applied after move type-changing Abilities,
        //        so it cannot affect an originally Normal-type move used by a Pokémon with Pixilate
        //        but can affect an originally Fairy-type move used by a Pokémon with Normalize.
    }
}
