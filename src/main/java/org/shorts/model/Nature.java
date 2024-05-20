package org.shorts.model;

import static org.shorts.model.StatEnum.ATK;
import static org.shorts.model.StatEnum.DEF;
import static org.shorts.model.StatEnum.SPATK;
import static org.shorts.model.StatEnum.SPDEF;
import static org.shorts.model.StatEnum.SPEED;

public enum Nature {
    HARDY(ATK, ATK),
    LONELY(ATK, DEF),
    BRAVE(ATK, SPEED),
    ADAMANT(ATK, SPATK),
    NAUGHTY(ATK, SPDEF),
    BOLD(DEF, ATK),
    DOCILE(DEF, DEF),
    RELAXED(DEF, SPEED),
    IMPISH(DEF, SPATK),
    LAX(DEF, SPDEF),
    TIMID(SPEED, ATK),
    HASTY(SPEED, DEF),
    SERIOUS(SPEED, SPEED),
    JOLLY(SPEED, SPATK),
    NAIVE(SPEED, SPDEF),
    MODEST(SPATK, ATK),
    MILD(SPATK, DEF),
    QUIET(SPATK, SPEED),
    BASHFUL(SPATK, SPATK),
    RASH(SPATK, SPDEF),
    CALM(SPDEF, ATK),
    GENTLE(SPDEF, DEF),
    SASSY(SPDEF, SPEED),
    CAREFUL(SPDEF, SPATK),
    QUIRKY(SPDEF, SPDEF);

    Nature(StatEnum increasedStat, StatEnum decreasedStat) {
        this.increasedStat = increasedStat;
        this.decreasedStat = decreasedStat;
    }

    private StatEnum increasedStat;
    private StatEnum decreasedStat;

    public StatEnum getIncreasedStat() {
        return increasedStat;
    }

    public StatEnum getDecreasedStat() {
        return decreasedStat;
    }

    public int getMultiplier(StatEnum stat) {
        if (stat == increasedStat) {
            return 110;
        } else if (stat == decreasedStat) {
            return 90;
        } else {
            return 100;
        }
    }

    //TODO: Implement later, maybe, if it comes up with the berries.
    //    private FlavorEnum favoriteFlavor;
    //    private FlavorEnum dislikedFlavor;
    //
    //    public FlavorEnum getFavoriteFlavor() {
    //        return favoriteFlavor;
    //    }
    //
    //    public FlavorEnum getDislikedFlavor() {
    //        return dislikedFlavor;
    //    }
}
