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
            weatherTurns--;
        }
    }

    public Trainer getPlayerOne() {
        return playerOne;
    }

    public Trainer getPlayerTwo() {
        return playerTwo;
    }

    public abstract void promptSwitchCausedByUserMove(Trainer trainer);

    public Trainer getCorrespondingTrainer(Pokemon pokemon) {
        if (this.playerOne.getLead() == pokemon) {
            return playerOne;
        } else {
            return playerTwo;
        }
    }

    public Trainer getOpposingTrainer(Trainer trainer) {
        if (this.playerOne == trainer) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    public Trainer getOpposingTrainer(Pokemon pokemon) {
        if (this.playerOne.getLead() == pokemon) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    public Pokemon getOpposingLead(Pokemon pokemon) {
        if (this.playerOne.getLead() == pokemon) {
            return playerTwo.getLead();
        } else {
            return playerOne.getLead();
        }
    }
}
