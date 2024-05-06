package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Scizor;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.PinchTypeBoostAbility.SWARM;
import static org.shorts.model.abilities.PinchTypeBoostAbility.TORRENT;
import static org.shorts.model.moves.Scald.SCALD;

class PinchTypeBoostAbilityTests {

    private SingleBattle battle;
    private Pokemon attacker;
    private Pokemon defender;

    @BeforeEach
    void setup() {
        attacker = new Squirtle(TORRENT);
        defender = new Scizor(SWARM);
        attacker.setCurrentHP(attacker.getMaxHP());
        attacker.setMoves(List.of(SCALD).toArray(new Move[1]));
        defender.setCurrentHP(defender.getMaxHP());
        defender.setMoves(List.of());
        battle = new SingleBattle(new Trainer("a", List.of(attacker)), new Trainer("b", List.of(defender)));

    }

    //    @Test
    //    void testActivatesForWaterMove() {
    //        battle.doMove(battle.getPlayerOne(), attacker.getMoves()[0],  battle.getPlayerTwo());
    //    }
}
