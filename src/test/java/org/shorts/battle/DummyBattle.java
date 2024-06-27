package org.shorts.battle;

import java.util.List;

import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;

public class DummyBattle extends Battle {

    public DummyBattle() {
        this(new Squirtle(TORRENT), new Bulbasaur(OVERGROW));
    }

    public DummyBattle(Pokemon user, Pokemon opponent) {
        super(new Trainer("Red", List.of(user)), new Trainer("Green", List.of(opponent)), 1);
    }

    public DummyBattle(List<Pokemon> userTeam, List<Pokemon> opponentTeam, int activeMonsPerSide) {
        super(
            new Trainer("Red", userTeam, activeMonsPerSide),
            new Trainer("Green", opponentTeam, activeMonsPerSide),
            activeMonsPerSide);
    }
}
