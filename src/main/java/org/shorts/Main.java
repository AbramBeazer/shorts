package org.shorts;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.shorts.battle.SingleBattle;
import org.shorts.battle.Trainer;
import org.shorts.model.moves.CloseCombat;
import org.shorts.model.moves.FreezeDry;
import org.shorts.model.moves.Move;
import org.shorts.model.moves.Scald;
import org.shorts.model.moves.Spore;
import org.shorts.model.moves.Thunder;
import org.shorts.model.pokemon.Bulbasaur;
import org.shorts.model.pokemon.Charmander;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.PinchTypeBoostAbility.BLAZE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;

public class Main {

    public static Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        Pokemon bulbasaur = new Bulbasaur(OVERGROW);
        bulbasaur.setMoves(List.of(new FreezeDry(), new Scald(), new Spore(), new Thunder()).toArray(new Move[4]));
        Pokemon charmander = new Charmander(BLAZE);
        bulbasaur.setMoves(List.of(new CloseCombat(), new Scald(), new Spore(), new Thunder()).toArray(new Move[4]));
        Trainer playerOne = new Trainer("Ash", List.of(bulbasaur));
        Trainer playerTwo = new Trainer("Gary", List.of(charmander));
        new SingleBattle(playerOne, playerTwo).run();
    }
}