package org.shorts.battle;

import java.io.IOException;

public abstract class Battle {

    protected int weatherTurns = Weather.INFINITE_WEATHER_DURATION;
    protected Weather weather = Weather.NONE;

    protected boolean weatherSuppressed = false;
    protected int terrainTurns = -1;
    protected Terrain terrain = Terrain.NONE;

    public abstract void run() throws IOException;

    public abstract void chooseLeads() throws IOException;

    public abstract void takeTurns() throws IOException;

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

    public void countdownWeather() {
        if (weatherTurns > 0) {
            weatherTurns--;
        }
        if (weatherTurns == 0) {
            System.out.println(weather.getDeactivationMessage());
            setWeather(Weather.NONE, Weather.INFINITE_WEATHER_DURATION);
        }
    }
}
