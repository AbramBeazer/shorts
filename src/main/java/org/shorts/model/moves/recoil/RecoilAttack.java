package org.shorts.model.moves.recoil;

import org.shorts.model.moves.Move;
import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.abilities.MagicGuard.MAGIC_GUARD;
import static org.shorts.model.abilities.RockHead.ROCK_HEAD;

public abstract class RecoilAttack extends Move {

    protected final double recoilPercentage;

    protected RecoilAttack(
        String name,
        double power,
        double accuracy,
        Type type,
        Move.Category category,
        Range range,
        int maxPP,
        boolean contact,
        int secondaryEffectChance,
        double recoilPercentage) {
        super(name, power, accuracy, type, category, range, maxPP, contact, secondaryEffectChance);
        this.recoilPercentage = recoilPercentage;
    }

    //TODO: If it hits a sub, is the damage 100, or the full calculated damage of the attack? I'm guessing it's 100, because KOing an enemy who's at 1HP doesn't deal massive recoil.
    //TODO: If the user of a recoil attack attacks first and makes itself faint due to recoil damage, the target will not attack or be subjected to recurrent damage during that round.
    //      Self-inflicted recoil damage from the previous turn can be countered if the target does not make a move on the following turn.
    @Override
    public void inflictRecoil(Pokemon user, int damageDealt) {
        if (user.getAbility() != ROCK_HEAD && user.getAbility() != MAGIC_GUARD) {
            user.takeDamage(
                (int) Math.ceil(damageDealt * this.recoilPercentage),
                String.format("%s was damaged by the recoil!", user.toString()));
        }
    }
}
