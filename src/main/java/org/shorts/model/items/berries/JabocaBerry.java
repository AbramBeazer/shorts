package org.shorts.model.items.berries;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class JabocaBerry extends Berry {

    private JabocaBerry() {
        super("Jaboca");
    }

    public static final JabocaBerry JABOCA_BERRY = new JabocaBerry();

    @Override
    public void afterHit(Pokemon user, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (!isUnnerveActive(user, battle) && move.getCategory().equals(Move.Category.PHYSICAL)) {
            eatOwnBerry(user);
            opponent.takeDamage(opponent.getMaxHP() / 8);
        }
    }

    @Override
    public boolean canDoEffect(Pokemon user) {
        return false;
    }

    @Override
    public void doEffect(Pokemon user) {

    }
}
