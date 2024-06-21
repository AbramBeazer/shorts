package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.status.VolatileStatusType;

public class MentalHerb extends HeldItem {

    private MentalHerb() {
        super("Mental Herb", 10);
    }

    public static final MentalHerb MENTAL_HERB = new MentalHerb();

    @Override
    public void afterStatus(Pokemon self, Pokemon opponent, Battle battle) {
        activate(self);
    }

    @Override
    public void onGainItem(Pokemon self, Battle battle) {
        activate(self);
    }

    private void activate(Pokemon self) {
        self.removeVolatileStatus(VolatileStatusType.INFATUATED);
        self.removeVolatileStatus(VolatileStatusType.TAUNTED);
        self.removeVolatileStatus(VolatileStatusType.ENCORED);
        self.removeVolatileStatus(VolatileStatusType.TORMENTED);
        self.removeVolatileStatus(VolatileStatusType.DISABLED);
        self.removeVolatileStatus(VolatileStatusType.HEAL_BLOCKED);
    }
}
