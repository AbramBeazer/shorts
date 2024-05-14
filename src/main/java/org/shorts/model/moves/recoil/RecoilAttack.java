package org.shorts.model.moves.recoil;

import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.RockHead.ROCK_HEAD;
import static org.shorts.model.moves.recoil.Struggle.STRUGGLE;

public interface RecoilAttack {

    double getRecoilPercentage();

    //TODO: If it hits a sub, is the damage 100, or the full calculated damage of the attack? I'm guessing it's 100, because KOing an enemy who's at 1HP doesn't deal massive recoil.
    //TODO: If the user of a recoil attack attacks first and makes itself faint due to recoil damage, the target will not attack or be subjected to recurrent damage during that round.
    //      Self-inflicted recoil damage from the previous turn can be countered if the target does not make a move on the following turn.
    default void inflictRecoil(Pokemon user, int damageDealt) {
        if (user.getAbility() != ROCK_HEAD || this == STRUGGLE) {
            user.takeDamage((int) Math.ceil(damageDealt * getRecoilPercentage()));
            System.out.println(user.getNickname() + " was hurt by the recoil!");
        }
    }

}
