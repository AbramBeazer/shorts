package org.shorts.model.moves.floating;

import java.util.List;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class FloatingMove extends Move {

    private Pokemon user;
    private int targetIndex;
    private int turnsRemaining;

    protected FloatingMove(
        String name,
        double power,
        double accuracy,
        Type type,
        Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance, Pokemon user, int targetIndex, int turnsRemaining) {

        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
        this.user = user;
        this.targetIndex = targetIndex;
        this.turnsRemaining = turnsRemaining;
    }

    public Pokemon getUser() {
        return user;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public void decrementTurnsRemaining() {
        turnsRemaining--;
    }

    @Override
    public void execute(Pokemon user, List<Pokemon> targets, Battle battle) {
        user.setMovedThisTurn(true);
        this.decrementPP();
        for (Pokemon target : targets) {
            //TODO:
            //  If a Pokémon uses Tera Blast while one of its opponents has Pressure, the additional PP will be deducted even if the Pressure Pokémon is not the move's target.
            //  Pressure increases the PP consumption of an opponent's Imprison and Snatch even though those are self-targeting moves; in Snatch's case the additional PP is consumed even if Snatch fails or snatches a move from a Pokémon other than the one with Pressure.
            if (pressureApplies(user, target, battle)) {
                this.decrementPP();
            }
        }

    }
}
