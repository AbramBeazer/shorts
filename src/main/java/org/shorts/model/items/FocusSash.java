package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.items.NoItem.NO_ITEM;

public class FocusSash extends HeldItem {

    private FocusSash() {
        super("Focus Sash");
    }

    public static final FocusSash FOCUS_SASH = new FocusSash();

    @Override
    public void afterHit(Pokemon self, Pokemon opponent, Battle battle, int previousHP, Move move) {
        if (self.getCurrentHP() == 0) {
            self.setCurrentHP(1);
            System.out.println(self.getNickname() + " held on due to Focus Sash!");
        }
        self.setHeldItem(NO_ITEM);
    }
}
