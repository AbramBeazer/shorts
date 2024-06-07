package org.shorts.battle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.shorts.model.moves.Range;
import org.shorts.model.pokemon.Pokemon;

public abstract class Battle {

    private final int activeMonsPerSide;

    protected final Trainer playerOne;
    protected final Trainer playerTwo;
    protected int weatherTurns = Weather.INFINITE_WEATHER_DURATION;
    protected Weather weather = Weather.NONE;

    protected boolean weatherSuppressed = false;
    protected int terrainTurns = -1;
    protected Terrain terrain = Terrain.NONE;
    protected int fairyLockTurns;
    protected int gravityTurns;

    public Battle(Trainer player1, Trainer player2, int activeMonsPerSide) {
        this.playerOne = player1;
        this.playerTwo = player2;
        this.activeMonsPerSide = activeMonsPerSide;
    }

    public abstract void run() throws Exception;

    public abstract void chooseLeads() throws IOException;

    public abstract void takeTurns() throws Exception;

    public int getWeatherTurns() {
        return weatherTurns;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather, int turns) {
        this.weather = weather;
        this.weatherTurns = turns;
    }

    public boolean isWeatherSuppressed() {
        return weatherSuppressed;
    }

    public void setWeatherSuppressed(boolean weatherSuppressed) {
        this.weatherSuppressed = weatherSuppressed;
    }

    public int getTerrainTurns() {
        return terrainTurns;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain, int turns) {
        this.terrain = terrain;
        this.terrainTurns = turns;
    }

    public int getFairyLockTurns() {
        return fairyLockTurns;
    }

    public void setFairyLockTurns(int fairyLockTurns) {
        this.fairyLockTurns = fairyLockTurns;
    }

    public int getGravityTurns() {
        return gravityTurns;
    }

    public void setGravityTurns(int gravityTurns) {
        this.gravityTurns = gravityTurns;
    }

    public void countDownWeather() {
        if (weatherTurns > 0) {
            weatherTurns--;
        }
        if (weatherTurns == 0) {
            System.out.println(weather.getDeactivationMessage());
            setWeather(Weather.NONE, Weather.INFINITE_WEATHER_DURATION);
        }
    }

    public void countDownTerrain() {
        if (terrainTurns > 0) {
            terrainTurns--;
        }
        if (terrainTurns == 0) {
            //            System.out.println(terrain.getDeactivationMessage());
            setTerrain(Terrain.NONE, Terrain.INFINITE_TERRAIN_DURATION);
        }
    }

    public void countDownFairyLock() {
        if (fairyLockTurns > 0) {
            fairyLockTurns--;
        }
    }

    public void countDownGravity() {
        if (gravityTurns > 0) {
            gravityTurns--;
        }
    }

    public Trainer getPlayerOne() {
        return playerOne;
    }

    public Trainer getPlayerTwo() {
        return playerTwo;
    }

    public abstract void promptSwitchCausedByUserMove(Trainer trainer);

    public abstract Trainer getCorrespondingTrainer(Pokemon pokemon);

    public abstract Trainer getOpposingTrainer(Trainer trainer);

    public abstract Trainer getOpposingTrainer(Pokemon pokemon);

    public abstract List<Pokemon> getOpposingActivePokemon(Pokemon pokemon);

    public abstract int getNumberOfActivePokemonWithVictoryStar(Trainer trainer);

    public int getActiveMonsPerSide() {
        return activeMonsPerSide;
    }

    public List<Pokemon> getPokemonWithinRange(Pokemon user, Range range) {
        final int activeMonsPerSide = getActiveMonsPerSide();
        final Trainer player = getCorrespondingTrainer(user);
        final Trainer opponent = getOpposingTrainer(user);
        final int userIndex = player.getTeam().indexOf(user);

        final List<Pokemon> possibleTargets = new ArrayList<>();

        switch (range) {
            case SELF:
            case VARIES:
                return List.of(user);
            case ALL:
            case BOTH_SIDES: //TODO: Figure out what needs to be different between ALL and BOTH_SIDES.
                final List<Pokemon> allPokemon = new ArrayList<>();
                allPokemon.addAll(player.getTeam().subList(0, activeMonsPerSide));
                allPokemon.addAll(opponent.getTeam().subList(0, activeMonsPerSide));
                return allPokemon;
            case OWN_PARTY:
                return player.getTeam();
            case OWN_SIDE:
                return player.getTeam().subList(0, activeMonsPerSide);
            case OTHER_SIDE:
            case RANDOM_OPPONENT:
                return opponent.getTeam().subList(0, activeMonsPerSide);
            case ALL_ALLIES_EXCEPT_SELF:
                final List<Pokemon> allies = player.getTeam().subList(0, activeMonsPerSide);
                allies.remove(userIndex);
                return allies;
            case ALL_ADJACENT_OPPONENTS:
            case RANDOM_ADJACENT_OPPONENT:
            case SINGLE_ADJACENT_OPPONENT:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(opponent.getTeam().get(i));
                    }
                }
                return possibleTargets;
            case ALL_ADJACENT:
            case SINGLE_ADJACENT_ANY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(opponent.getTeam().get(i));
                        if (i != userIndex) {
                            possibleTargets.add(player.getTeam().get(i));
                        }
                    }
                }
                return possibleTargets;
            case SINGLE_ANY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    possibleTargets.add(opponent.getTeam().get(i));
                    if (i != userIndex) {
                        possibleTargets.add(player.getTeam().get(i));
                    }
                }
                return possibleTargets;
            case SINGLE_ADJACENT_ALLY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1 && i != userIndex) {
                        possibleTargets.add(player.getTeam().get(i));
                    }
                }
                return possibleTargets;
            case SINGLE_SELF_OR_ADJACENT_ALLY:
                for (int i = 0; i < activeMonsPerSide; i++) {
                    if (Math.abs(i - userIndex) <= 1) {
                        possibleTargets.add(player.getTeam().get(i));
                    }
                }
                return possibleTargets;
            default:
                return possibleTargets;
        }
    }

    public void printField(Trainer player) {
        final Trainer opponent = this.getOpposingTrainer(player);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getActiveMonsPerSide(); i++) {
            sb.append("                            ");
        }
        final String blankSpace = sb.toString();

        final StringBuilder field = new StringBuilder("*" + blankSpace + "OPPONENT" + blankSpace + "*")
            .append("\n|");

        for (int i = 0; i < getActiveMonsPerSide(); i++) {
            final Pokemon mon = opponent.getTeam().get(i);
            final String name = mon.getNickname() + " (" + mon.getPokedexEntry().getSpeciesName() + ")";
            field.append(blankSpace).append(name);
        }

        System.out.println(field);
    }
}
