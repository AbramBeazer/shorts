package org.shorts.model.abilities.elementabsorb;

import org.shorts.battle.Battle;
import org.shorts.model.StatEnum;
import org.shorts.model.abilities.Ability;
import org.shorts.model.abilities.IgnorableAbility;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class ElementAbsorbRaiseStatAbility extends Ability implements IgnorableAbility {

    private Type type;
    private StatEnum stat;

    protected ElementAbsorbRaiseStatAbility(String name, Type type, StatEnum stat) {
        super(name);
        this.type = type;
        this.stat = stat;
    }

    @Override
    public double beforeHit(Pokemon self, Pokemon opponent, Battle battle, Move move) {
        if (move.getType().equals(type) && self.canChangeStat(1, stat)) {
            self.changeStat(1, stat);
            return 0;
        }
        return 1;
    }

    public Type getType() {
        return type;
    }

    public static final ElementAbsorbRaiseStatAbility MOTOR_DRIVE = new ElementAbsorbRaiseStatAbility(
        "Motor Drive",
        Type.ELECTRIC,
        StatEnum.SPEED);
    public static final ElementAbsorbRaiseStatAbility SAP_SIPPER = new ElementAbsorbRaiseStatAbility(
        "Sap Sipper",
        Type.GRASS,
        StatEnum.ATK);
}
