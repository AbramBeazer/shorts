package org.shorts.model.items;

import org.shorts.battle.Battle;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;

public class AirBalloon extends HeldItem {

    private AirBalloon() {
        super("Air Balloon");
    }

    public static final AirBalloon AIR_BALLOON = new AirBalloon();

    @Override
    public void afterHit(Pokemon user, Pokemon opponent, Battle battle, int previousHP, Move move) {
        //TODO: This should activate if the holder or its substitute is hit with a damaging move.
        user.setHeldItem(NoItem.NO_ITEM);
        System.out.println(user.getNickname() + "'s Air Balloon popped!");
    }
}
