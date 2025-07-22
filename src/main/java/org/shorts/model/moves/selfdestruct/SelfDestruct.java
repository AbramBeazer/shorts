package org.shorts.model.moves.selfdestruct;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.abilities.Damp;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.Damp.*;

public class SelfDestruct extends Move {

    public SelfDestruct() {
        super("Self-Destruct", 200, 100, Type.NORMAL, Category.PHYSICAL, Range.ALL_ADJACENT, 8, false, 100);
    }

    private int hits;

    //TODO: User deals damage, then faints.
    //TODO: Nothing happens if the move misses, if the target is immune, or if Damp is present.

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.setMovedThisTurn(true);
        this.decrementPP();
        if (targets.stream()
            .allMatch(target -> Type.getTypeMultiplier(this.getType(), target.getTypes()) == Type.IMMUNE)
            || targets.stream().anyMatch(target -> target.getAbility() == DAMP)) {
            System.out.println("But it failed!");
        } else if (targets.isEmpty()) {
            user.setCurrentHP(0);
            System.out.println(user + " fainted!");
        } else {
            for (Pokemon target : targets) {
                //TODO:
                //  If a Pokémon uses Tera Blast while one of its opponents has Pressure, the additional PP will be deducted even if the Pressure Pokémon is not the move's target.
                //  Pressure increases the PP consumption of an opponent's Imprison and Snatch even though those are self-targeting moves; in Snatch's case the additional PP is consumed even if Snatch fails or snatches a move from a Pokémon other than the one with Pressure.
                if (pressureApplies(user, target, battle)) {
                    this.decrementPP();
                }
            }

            hits = 0;
            for (Pokemon target : targets) {
                executeOnTarget(user, target, battle);
                //TODO: Handle Endure
                if (target.getCurrentHP() == 0) {
                    //TODO: Or should I have this call in Pokemon.takeDamage()?
                    System.out.println(target + " fainted!");
                    target.afterFaint(user, battle);
                    user.afterKO(target, battle);
                }
            }

            if(hits > 0) {
                user.setCurrentHP(0);
                System.out.println(user + " fainted!");
            }
        }
        user.setLastMoveUsed(this);
    }

    @Override
    protected boolean rollToHit(Pokemon user, Pokemon target, Battle battle) {
        boolean success = super.rollToHit(user, target, battle);
        if (success) {
            hits++;
        }
        return success;
    }
}
