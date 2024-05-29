package org.shorts.model.moves;

import java.util.List;
import java.util.stream.Collectors;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.Status;
import org.shorts.model.types.Type;

public class BeatUp extends Move {

    private int currentAttackerIndex = 0;
    private List<Pokemon> viableAttackers;

    public BeatUp() {
        super("Beat Up", -1, 100, Type.DARK, Category.PHYSICAL, Range.SINGLE_ADJACENT_ANY, 16, false, 0);
    }

    @Override
    protected void executeMove(Pokemon user, Pokemon target, Battle battle) {
        final int previousTargetHP = target.getCurrentHP();

        viableAttackers = battle.getCorrespondingTrainer(user).getTeam()
            .stream()
            .filter(p -> !p.hasFainted() && p.getStatus() == Status.NONE)
            .collect(
                Collectors.toList());

        while (currentAttackerIndex < viableAttackers.size() && !user.hasFainted() && !target.hasFainted()) {
            int damage = calculateDamage(user, target, battle);
            target.takeDamage(damage);

            if (!user.hasFainted()) {
                this.inflictRecoil(user, damage);
            }

            target.afterHit(user, battle, previousTargetHP, this);
        }
        viableAttackers.clear();
        currentAttackerIndex = 0;

        this.trySecondaryEffect(user, target, battle);

        if (!user.hasFainted()) {
            user.afterAttack(target, battle, this);
        }

    }

    @Override
    protected int calculateDamage(Pokemon user, Pokemon target, Battle battle) {

        double movePower = calculateMovePower(user, target, battle);
        //TODO: Critical hits should ignore attack drops and defense buffs.
        double attack = user.calculateAttack();

        //TODO: is this right? Are we still using the target's base DEF even though we use the attacker's calculated attack?
        double defense = target.getPokedexEntry().getBaseDef();

        double baseDamage = ((0.4 * user.getLevel() + 2) * movePower * (attack / defense) * 0.02) + 2;
        return applyMultipliers(user, target, battle, baseDamage);
    }

    @Override
    public double getPower() {
        return 5 + (viableAttackers.get(currentAttackerIndex).getPokedexEntry().getBaseAtk() / 10d);
    }
}