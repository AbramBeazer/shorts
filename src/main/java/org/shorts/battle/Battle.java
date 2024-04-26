package org.shorts.battle;

import java.io.IOException;

public abstract class Battle {

    protected int weatherTurns = 0;
    protected Weather weather = Weather.NONE;
    protected int terrainTurns = 0;
    protected Terrain terrain = Terrain.NONE;

    public abstract void run() throws IOException;

    public abstract void chooseLeads() throws IOException;

    public abstract void takeTurns() throws IOException;

    public int getWeatherTurns() {
        return weatherTurns;
    }

    public void setWeatherTurns(int weatherTurns) {
        this.weatherTurns = weatherTurns;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public int getTerrainTurns() {
        return terrainTurns;
    }

    public void setTerrainTurns(int terrainTurns) {
        this.terrainTurns = terrainTurns;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
