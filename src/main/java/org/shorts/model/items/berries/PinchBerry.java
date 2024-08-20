package org.shorts.model.items.berries;

import org.shorts.model.StatEnum;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.Ripen.RIPEN;

public class PinchBerry extends Berry {

    private StatEnum stat;

    private PinchBerry(String name, StatEnum stat) {
        super(name, .25);
        this.stat = stat;
    }

    @Override
    public void doEffect(Pokemon user) {
        final int stages = user.getAbility() == RIPEN ? 2 : 1;
        user.changeStat(stages, stat);
        super.doEffect(user);
    }

    public static final PinchBerry LIECHI_BERRY = new PinchBerry("Liechi", StatEnum.ATK);
    public static final PinchBerry PETAYA_BERRY = new PinchBerry("Petaya", StatEnum.SPATK);
    public static final PinchBerry SALAC_BERRY = new PinchBerry("Salac", StatEnum.SPEED);
}
