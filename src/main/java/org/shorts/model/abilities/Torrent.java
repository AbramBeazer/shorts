package org.shorts.model.abilities;

import org.shorts.battle.Battle;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.types.Type;

public class Torrent extends Ability {

    public Torrent() {
        super("Torrent");
    }

    public static final Torrent TORRENT = new Torrent();

    @Override
    public void beforeAttack(Pokemon self, Pokemon opponent, Battle battle, Integer damage, Type moveType) {
        if (self.getCurrentHP() <= self.getMaxHP() / 3 && moveType.equals(Type.WATER)) {
            damage = (int) (damage * 1.5);
        }
    }
}
