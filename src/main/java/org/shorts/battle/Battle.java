package org.shorts.battle;

import java.io.IOException;

import org.shorts.model.pokemon.Pokemon;

public abstract class Battle {

    protected final Trainer playerOne;
    protected final Trainer playerTwo;
    protected int weatherTurns = Weather.INFINITE_WEATHER_DURATION;
    protected Weather weather = Weather.NONE;

    protected boolean weatherSuppressed = false;
    protected int terrainTurns = -1;
    protected Terrain terrain = Terrain.NONE;
    protected int fairyLockTurns;
    protected int gravityTurns;

    public Battle(Trainer player1, Trainer player2) {
        this.playerOne = player1;
        this.playerTwo = player2;
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

    public abstract Pokemon getOpposingLead(Pokemon pokemon);

    public abstract int getNumberOfActivePokemonWithVictoryStar(Trainer trainer);
}
