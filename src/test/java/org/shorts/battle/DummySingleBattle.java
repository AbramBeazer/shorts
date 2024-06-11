package org.shorts.battle;

import java.util.List;

import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;

public class DummySingleBattle extends Battle {

    public DummySingleBattle() {
        this(new Squirtle(TORRENT), new Bulbasaur(OVERGROW));
    }

    public DummySingleBattle(Pokemon user, Pokemon opponent) {
        super(new Trainer("Red", List.of(user)), new Trainer("Green", List.of(opponent)), 1);
    }
}
