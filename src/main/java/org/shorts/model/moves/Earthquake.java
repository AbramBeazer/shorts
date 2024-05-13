package org.shorts.model.moves;

import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatus.VolatileStatusType.SEMI_INVULNERABLE;

public class Earthquake extends PhysicalMove {

    private Earthquake() {
        super("Earthquake", 100, 100, Type.GROUND, 16, false, 0);
    }

    @Override
    public int applyMultipliers(Pokemon attacker, Pokemon defender, Battle battle, int baseDamage) {
        int multiplier = super.applyMultipliers(attacker, defender, battle, baseDamage);
        if (defender.getVolatileStatuses()
            .stream()
            .anyMatch(vs -> vs.getType() == SEMI_INVULNERABLE && vs.getMove() instanceof Dig)) {
            multiplier *= 2;
        }
        if (battle.getTerrain().equals(Terrain.GRASSY)) {
            multiplier *= 0.5;
        }
        return multiplier;
    }

    public static final Earthquake EARTHQUAKE = new Earthquake();
}
