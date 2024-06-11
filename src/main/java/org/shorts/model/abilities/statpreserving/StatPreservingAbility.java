package org.shorts.model.abilities.statpreserving;

import org.shorts.model.StatEnum;
import org.shorts.model.abilities.Ability;

public class StatPreservingAbility extends Ability {

    protected StatEnum stat;

    protected StatPreservingAbility(String name, StatEnum stat) {
        super(name);
        this.stat = stat;
    }

    @Override
    public boolean isDropPossible(StatEnum stat) {
        if (this.stat == null) {
            return false;
        } else {
            return this.stat != stat;
        }
    }

    public static final StatPreservingAbility FULL_METAL_BODY = new StatPreservingAbility("Full Metal Body", null);

}
