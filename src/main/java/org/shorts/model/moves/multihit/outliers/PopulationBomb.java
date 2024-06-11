package org.shorts.model.moves.multihit.outliers;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.moves.SlicingMove;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.SubstituteStatus;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.SkillLink.SKILL_LINK;
import static org.shorts.model.items.LoadedDice.LOADED_DICE;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class PopulationBomb extends Move implements SlicingMove {

    private static final int MAX_HITS = 10;

    public PopulationBomb() {
        super("Population Bomb", 20, 90, Type.NORMAL, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, true, 0);
    }

    @Override
    protected void executeOnTarget(Pokemon user, Pokemon target, Battle battle) {
        final boolean skipRollToHit;
        int maxHitsOverride = MAX_HITS;
        if (user.getAbility() == SKILL_LINK) {
            skipRollToHit = true;
        } else if (user.getHeldItem() == LOADED_DICE) {
            maxHitsOverride = Main.RANDOM.nextInt(7) + 4;
            skipRollToHit = true;
        } else {
            skipRollToHit = false;
        }

        if (rollToHit(user, target, battle)) {

            int hitNum = 0;
            while (hitNum < maxHitsOverride && !user.hasFainted() && !target.hasFainted()) {
                final int previousTargetHP = target.getCurrentHP();

                int damage = calculateDamage(user, target, battle);
                if (target.hasVolatileStatus(SUBSTITUTE)) { //TODO: Handle moves and abilities that ignore substitute.
                    ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).takeDamage(damage);
                } else {
                    target.takeDamage(damage);
                }

                if (!user.hasFainted()) {
                    this.inflictRecoil(user, damage);
                }

                //TODO: Verify which effects should happen after the attack hits the sub and which shouldn't.
                if (!target.hasVolatileStatus(SUBSTITUTE)) {
                    target.afterHit(user, battle, previousTargetHP, this);
                }

                if (target.hasVolatileStatus(SUBSTITUTE)
                    && ((SubstituteStatus) target.getVolatileStatus(SUBSTITUTE)).getSubHP() == 0) {
                    target.removeVolatileStatus(SUBSTITUTE);
                }

                hitNum++;
                if (!skipRollToHit && !rollToHit(user, target, battle)) {
                    break;
                }
            }
            if (hitNum > 1) {
                System.out.println("Hit " + hitNum + " times!");
            }

            if (!user.hasFainted()) {
                user.afterAttack(target, battle, this);
            }
        }
    }

}