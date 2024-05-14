package org.shorts.model.abilities;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.AbstractStatus;
import org.shorts.model.status.Status;
import org.shorts.model.status.VolatileStatus;

import static org.shorts.model.status.VolatileStatus.INFATUATED;
import static org.shorts.model.status.VolatileStatusType.SUBSTITUTE;

public class ContactStatusAbility extends Ability {

    private AbstractStatus status;

    private ContactStatusAbility(String name, AbstractStatus status) {
        super(name);
        this.status = status;
    }

    public static final ContactStatusAbility STATIC = new ContactStatusAbility("Static", Status.PARALYZE);
    public static final ContactStatusAbility FLAME_BODY = new ContactStatusAbility("Flame Body", Status.BURN);
    public static final ContactStatusAbility POISON_POINT = new ContactStatusAbility("Poison Point", Status.POISON);
    public static final ContactStatusAbility CUTE_CHARM = new ContactStatusAbility("Cute Charm", INFATUATED);

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (move.isContact() && Main.RANDOM.nextInt(100) < 30 && !self.hasVolatileStatus(SUBSTITUTE)
            && (status.isStatusPossible(opponent, battle))) {
            //TODO: Test this. I'm pretty sure there aren't any problems here. Status.isStatusPossible won't have to worry about nullified abilities since this Pokemon clearly doesn't have Mold Breaker, but rather Cute Charm or something.
            if (status instanceof VolatileStatus) {
                opponent.addVolatileStatus((VolatileStatus) status);
            } else {
                opponent.setStatus((Status) status);
            }

        }
    } //TODO: Not sure if this should be beforeHit or afterHit. It depends on if the effect should activate even if the attack causes this Pokémon to faint.
    //       I just don't want Static to paralyze an attacker and then give them a Guts boost.
}
