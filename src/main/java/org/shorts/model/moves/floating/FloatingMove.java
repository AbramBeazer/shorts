package org.shorts.model.moves.floating;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FloatingMove extends Move {

    private int duration;

    protected FloatingMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance, int duration) {

        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.setMovedThisTurn(true);
        this.decrementPP();
        if (targets.isEmpty()) {
            System.out.println("But there was no target...");
            return;
        }
        for (Pokemon target : targets) {
            //TODO:
            //  If a Pokémon uses Tera Blast while one of its opponents has Pressure, the additional PP will be deducted even if the Pressure Pokémon is not the move's target.
            //  Pressure increases the PP consumption of an opponent's Imprison and Snatch even though those are self-targeting moves; in Snatch's case the additional PP is consumed even if Snatch fails or snatches a move from a Pokémon other than the one with Pressure.
            if (pressureApplies(user, target, battle)) {
                this.decrementPP();
            }
        }

        Pokemon target = targets.get(0);
        Trainer targetTrainer = battle.getCorrespondingTrainer(target);
        int targetIndex;
        if (battle.getCorrespondingTrainer(user) == targetTrainer) {
            targetIndex = targetTrainer.getActivePokemon().indexOf(target) + battle.getActiveMonsPerSide();
        } else {
            targetIndex = targetTrainer.getActivePokemon().indexOf(target);
        }

        battle.getFloatingEffects().add(new FloatingEffect(this, user, targetIndex, duration));
    }

    public void triggerFloatingEffect(Pokemon user, Pokemon target, Battle battle) {
        this.executeOnTarget(user, target, battle);

        if (user.getCurrentHP() == 0) {
            user.afterFaint(battle);
        }

        //TODO: Handle Endure, Destiny Bond, etc.
        if (target.getCurrentHP() == 0) {
            //Or should I have this call in Pokemon.takeDamage()?
            System.out.println(target + " fainted!");
            target.afterFaint(battle);
            if (!user.hasFainted()) {
                user.afterKO(target, battle);
            }
        }
    }
}
