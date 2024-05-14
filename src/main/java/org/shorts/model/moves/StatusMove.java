package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Prankster.PRANKSTER;

public abstract class StatusMove extends Move {

    private final boolean onlyTargetSelf;

    protected StatusMove(String name, double accuracy, Type type, int maxPP, boolean onlyTargetSelf) {
        super(name, 0, accuracy, type, maxPP, false, 100);
        this.onlyTargetSelf = onlyTargetSelf;
    }

    @Override
    public int getPriority(Pokemon attacker, Pokemon defender, Battle battle) {
        /* TODO:
            Dark-type Pokémon are now immune to opposing Pokémon's moves that gain priority due to Prankster, including moves called by moves that call other moves
            (such as Assist and Nature Power) and excluding moves that are repeated as a result of Prankster-affected Instruct
            or moves that occur earlier than their usual order due to Prankster-affected After You. Ally Dark-type Pokémon are still affected by the user's status moves.
            Dark-type Pokémon can still bounce moves back with Magic Bounce or Magic Coat; moves that have increased priority due to Prankster which are reflected
            by Magic Bounce or Magic Coat can affect Dark-type Pokémon, unless the Pokémon that bounced the move with Magic Coat also has Prankster.
            Moves that target all Pokémon (except Perish Song and Rototiller, which cannot affect Dark-type opponents if boosted by Prankster) and moves that set traps are successful regardless of the presence of Dark-type Pokémon.
         */
        if (attacker.getAbility() == PRANKSTER) {
            return 1;
        } else {
            return super.getPriority(attacker, defender, battle);
        }
    }

    @Override
    protected boolean pressureApplies(Pokemon userMon) {
        return super.pressureApplies(userMon) && !onlyTargetSelf;
    }
}
