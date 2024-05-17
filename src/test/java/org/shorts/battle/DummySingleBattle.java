package org.shorts.battle;

import java.util.List;

import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;

public class DummySingleBattle extends SingleBattle {

    public DummySingleBattle() {
        super(
            new Trainer("Red", List.of(new Squirtle(TORRENT))),
            new Trainer("Green", List.of(new Bulbasaur(OVERGROW))));
    }
}
