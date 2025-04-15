package org.shorts.model.moves.multihit.outliers;

import java.util.List;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class BeatUp extends Move {

    private int currentAttackerIndex = 0;
    private List<Pokemon> viableAttackers;

    public BeatUp() {
        super("Beat Up", -1, 100, Type.DARK, Category.PHYSICAL, Range.NORMAL, 16, false, 0);
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        if (rollToHit(user, target, battle)) {

            viableAttackers = battle.getCorrespondingTrainer(user)
                .getTeam()
                .stream()
                .filter(p -> !p.hasFainted() && p.getStatus() == Status.NONE)
                .collect(Collectors.toList());

            while (currentAttackerIndex < viableAttackers.size() && !user.hasFainted() && !target.hasFainted()) {
                final int previousTargetHP = target.getCurrentHP();

                int damage = calculateDamage(user, target, battle);

                final boolean hitSub = checkForHitSub(user, target);
                if (hitSub) {
                    ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).takeDamage(damage);
                } else {
                    target.takeDamage(damage);
                }

                if (!user.hasFainted()) {
                    this.inflictRecoil(user, damage);
                }

                if (!hitSub) {
                    target.afterHit(user, battle, previousTargetHP, this);
                }

                if (target.hasVolatileStatus(SUBSTITUTE)
                    && ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).getSubHP() == 0) {
                    target.removeVolatileStatus(SUBSTITUTE);
                }

                currentAttackerIndex++;
            }

            viableAttackers.clear();
            currentAttackerIndex = 0;

            if (!user.hasFainted()) {
                user.afterAttack(target, battle, this);
            }
        }
    }

    @Override
    protected double getDefendingStat(Pokemon user, Pokemon target, Battle battle) {
        //TODO: is this right? Are we still using the target's base DEF even though we use the attacker's calculated attack?
        return target.getPokedexEntry().getBaseDef();
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        return 5 + (viableAttackers.get(currentAttackerIndex).getPokedexEntry().getBaseAtk() / 10d);
    }
}