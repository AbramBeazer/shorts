package org.shorts.model.moves;

import org.shorts.Main;
import org.shorts.battle.Battle;
import org.shorts.battle.Terrain;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

import static org.shorts.model.status.VolatileStatusType.SEMI_INVULNERABLE;

public class Magnitude extends Move implements CanHitDig, PowerVaries {

    public Magnitude() {
        super("Magnitude", 0, 100, Type.GROUND, Category.PHYSICAL, Range.ALL_ADJACENT, 48, false, 0);
    }

    @Override
    public double getPower(Pokemon user, Pokemon target, Battle battle) {
        //TODO: Should this be implemented? The move isn't in Gen 8 or 9.
        final int random = Main.RANDOM.nextInt(20);
        if (random == 0) {
            System.out.println("Magnitude 4!");
            return 10;
        } else if (random <= 2) {
            System.out.println("Magnitude 5!");
            return 30;
        } else if (random <= 6) {
            System.out.println("Magnitude 6!");
            return 50;
        } else if (random <= 12) {
            System.out.println("Magnitude 7!");
            return 70;
        } else if (random <= 16) {
            System.out.println("Magnitude 8!");
            return 90;
        } else if (random <= 18) {
            System.out.println("Magnitude 9!");
            return 110;
        } else {
            System.out.println("Magnitude 10!");
            return 150;
        }
    }

    //TODO: I think this should halve the damage, not the power. This logic is in the wrong place.
    @Override
    protected double getPowerMultipliers(Pokemon user, Pokemon target, Battle battle) {
        final double multiplier =
            battle.getTerrain() == Terrain.GRASSY && target.isGrounded() && !target.hasVolatileStatus(SEMI_INVULNERABLE)
                ? 2
                : 1;
        //TODO: Does terrain affect a mon with an Iron Ball or Thousand Arrows?
        return multiplier * super.getPowerMultipliers(user, target, battle);
    }
}
