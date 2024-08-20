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
        if (tryEatingOwnBerry(user, battle)) {
            opponent.takeDamage(opponent.getMaxHP() / 8);
        }
    }

    @Override
    public void afterTurn(Pokemon user, Battle battle) {
        return;
    }
}
