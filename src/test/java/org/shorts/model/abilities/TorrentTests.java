package org.shorts.model.abilities;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Medicham;
import org.shorts.model.pokemon.Pokemon;
import org.shorts.model.pokemon.Squirtle;

import static org.shorts.model.abilities.AttackDoublingAbility.PURE_POWER;
import static org.shorts.model.abilities.Torrent.TORRENT;
import static org.shorts.model.moves.Scald.SCALD;
import static org.shorts.model.moves.Thunder.THUNDER;

class TorrentTests {

    private SingleBattle battle;
    private Pokemon attacker;
    private Pokemon defender;

    @BeforeEach
    void setup() {
        attacker = new Squirtle(TORRENT);
        defender = new Medicham(PURE_POWER);
        attacker.setCurrentHP(attacker.getMaxHP());
        attacker.setMoves(List.of(SCALD, THUNDER).toArray(new Move[2]));
        battle = new SingleBattle(new Trainer("a", List.of(attacker)), new Trainer("b", List.of(defender)));

    }

    //    @Test
    //    void testActivatesForWaterMove() {
    //        battle.doMove(battle.getPlayerOne(), attacker.getMoves()[0],  battle.getPlayerTwo());
    //    }
}
