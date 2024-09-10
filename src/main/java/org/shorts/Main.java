package org.shorts;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.shorts.battle.Battle;
import org.shorts.battle.Trainer;
import org.shorts.model.Nature;
import org.shorts.model.abilities.Regenerator;
import org.shorts.model.moves.Bite;
import org.shorts.model.moves.Earthquake;
import org.shorts.model.moves.Ember;
import org.shorts.model.moves.Psyshock;
import org.shorts.model.moves.RazorLeaf;
import org.shorts.model.moves.Rest;
import org.shorts.model.moves.ShadowBall;
import org.shorts.model.moves.SludgeBomb;
import org.shorts.model.moves.SubstituteMove;
import org.shorts.model.moves.Tackle;
import org.shorts.model.moves.entryhazardsetter.StealthRock;
import org.shorts.model.moves.multihit.RockBlast;
import org.shorts.model.moves.recoil.DoubleEdge;
import org.shorts.model.moves.recoil.FlareBlitz;
import org.shorts.model.moves.thawing.Scald;
import org.shorts.model.moves.weather.SunnyDay;
import org.shorts.model.pokemon.Pokedex;
import org.shorts.model.pokemon.Pokemon;

import static org.shorts.model.abilities.PinchTypeBoostAbility.BLAZE;
import static org.shorts.model.abilities.PinchTypeBoostAbility.OVERGROW;
import static org.shorts.model.abilities.RockHead.ROCK_HEAD;

public class Main {

    public static final DecimalFormat DECIMAL = new DecimalFormat("0.0");
    public static Random RANDOM = new Random();
    public static Random HIT_RANDOM = new Random();
    public static Random CRIT_RANDOM = new Random();
    public static Random DAMAGE_RANDOM = new Random();

    public static void main(String[] args) throws Exception {
        Pokedex.create();
        final int activeMonsPerSide = 1;

        Pokemon bulbasaur = new Pokemon(
            Pokedex.get("Bulbasaur"),
            100,
            new int[] { 4, 0, 252, 252, 0, 0 },
            Nature.QUIRKY,
            OVERGROW);
        bulbasaur.setMoves(List.of(new RazorLeaf(), new Tackle(), new SludgeBomb(), new Rest()));

        Pokemon slowbro = new Pokemon(
            Pokedex.get("Slowbro"),
            100,
            new int[] { 252, 0, 128, 0, 128, 0 },
            Nature.BOLD,
            Regenerator.REGENERATOR);
        slowbro.setMoves(List.of(new Scald(), new Psyshock(), new ShadowBall(), new Bite()));

        Pokemon charmander = new Pokemon(
            Pokedex.get("Charmander"),
            100,
            new int[] { 4, 0, 0, 252, 0, 252 },
            Nature.TIMID,
            BLAZE);
        charmander.setMoves(List.of(new Ember(), new FlareBlitz(), new SunnyDay(), new SubstituteMove()));

        Pokemon rhydon = new Pokemon(
            Pokedex.get("Rhydon"),
            100,
            new int[] { 252, 252, 4, 0, 0, 0 },
            Nature.IMPISH,
            ROCK_HEAD);
        rhydon.setMoves(List.of(new Earthquake(), new RockBlast(), new DoubleEdge(), new StealthRock()));

        List<Pokemon> teamOne = new ArrayList<>();
        teamOne.add(bulbasaur);
        teamOne.add(slowbro);
        List<Pokemon> teamTwo = new ArrayList<>();
        teamTwo.add(charmander);
        teamTwo.add(rhydon);
        Trainer playerOne = new Trainer("Ash", teamOne, activeMonsPerSide);
        Trainer playerTwo = new Trainer("Gary", teamTwo, activeMonsPerSide);
        new Battle(playerOne, playerTwo, activeMonsPerSide).run();
    }
}