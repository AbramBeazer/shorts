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
    public void onGainAbility(Pokemon self) {
        self.addVolatileStatus(VolatileStatus.MAGIC_COAT);
    }

    @Override
    public void onLoseAbility(Pokemon self, Pokemon opponent, Battle battle) {
        self.removeVolatileStatus(VolatileStatusType.MAGIC_COAT);
    }
}


