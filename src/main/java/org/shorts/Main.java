package org.shorts;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.Move;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Charmander;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.moves.CloseCombat.CLOSE_COMBAT;
import static org.shorts.model.moves.FreezeDry.FREEZE_DRY;
import static org.shorts.model.moves.Scald.SCALD;
import static org.shorts.model.moves.Spore.SPORE;
import static org.shorts.model.moves.Thunder.THUNDER;

public class Main {

    public static Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        Pokemon bulbasaur = new Bulbasaur();
        bulbasaur.setMoves(List.of(FREEZE_DRY, SCALD, SPORE, THUNDER).toArray(new Move[4]));
        Pokemon charmander = new Charmander();
        bulbasaur.setMoves(List.of(CLOSE_COMBAT, SCALD, SPORE, THUNDER).toArray(new Move[4]));
        Trainer playerOne = new Trainer("Ash", List.of(bulbasaur));
        Trainer playerTwo = new Trainer("Gary", List.of(charmander));
        new SingleBattle(playerOne, playerTwo).run();
    }
}