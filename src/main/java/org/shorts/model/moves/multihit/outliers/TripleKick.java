package org.shorts.model.moves.multihit.outliers;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.SkillLink.SKILL_LINK;
import static org.shorts.model.items.LoadedDice.LOADED_DICE;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class TripleKick extends Move {

    private static final int MAX_HITS = 3;
    private int hitNum;

    public TripleKick() {
        super("Triple Kick", 10, 90, Type.FIGHTING, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        final boolean skipRollToHit = user.getAbility() == SKILL_LINK || user.getHeldItem() == LOADED_DICE;

        if (rollToHit(user, target, battle)) {

            hitNum = 0;
            while (hitNum < MAX_HITS && !user.hasFainted() && !target.hasFainted()) {
                final int previousTargetHP = target.getCurrentHP();

                hitNum++;
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

                //TODO: Verify which effects should happen after the attack hits the sub and which shouldn't.
                if (!hitSub) {
                    target.afterHit(user, battle, previousTargetHP, this);
                }

                if (target.hasVolatileStatus(SUBSTITUTE)
                    && ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).getSubHP() == 0) {
                    target.removeVolatileStatus(SUBSTITUTE);
                }

                if (!skipRollToHit && !rollToHit(user, target, battle)) {
                    break;
                }
            }
            if (hitNum > 1) {
                System.out.println("Hit " + hitNum + " times!");
            }
            hitNum = 0;

            if (!user.hasFainted()) {
                user.afterAttack(target, battle, this);
            }
        }
    }

    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        return super.getPowerMultipliers(user, target, battle) * hitNum;
    }
}
