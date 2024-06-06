package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatus;
import org.shorts.model.status.VolatileStatusType;

public class MagicBounce extends Ability implements IgnorableAbility {

    private MagicBounce() {
        super("Magic Bounce");
    }

    public static final MagicBounce MAGIC_BOUNCE = new MagicBounce();

    @Override
    public void onGainAbility(Pokemon self, Battle battle) {
        self.addVolatileStatus(new VolatileStatus(VolatileStatusType.MAGIC_COAT, -1));
    }

    @Override
    public void onLoseAbility(Pokemon self, Pokemon opponent, Battle battle) {
        self.removeVolatileStatus(VolatileStatusType.MAGIC_COAT);
    }

    //TODO: Rework this. I'll probably need to add afterEntry, but I think this'll need a rework with how it interacts with ability suppressing/ignoring.
}


