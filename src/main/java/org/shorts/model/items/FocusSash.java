package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.items.NoItem.NO_ITEM;

public class FocusSash extends HeldItem {

    private FocusSash() {
        super("Focus Sash", 10);
    }

    public static final FocusSash FOCUS_SASH = new FocusSash();

    @Override
    public void afterHit(Pokemon user, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (user.getCurrentHP() == 0) {
            user.setCurrentHP(1);
            System.out.println(user.getNickname() + " held on due to Focus Sash!");
        }
        user.setHeldItem(NO_ITEM);
    }
}
