package org.shorts.battle;

import java.io.IOException;

import org.shorts.model.types.Type;

public abstract class Battle {

    protected int weatherTurns = 0;
    protected Type weatherType = null;
    protected int terrainTurns = 0;
    protected Type terrainType = null;

    public abstract void run() throws IOException;

    public abstract void chooseLeads() throws IOException;

    public abstract void takeTurns() throws IOException;

    public int getWeatherTurns() {
        return weatherTurns;
    }

    public void setWeatherTurns(int weatherTurns) {
        this.weatherTurns = weatherTurns;
    }

    public Type getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(Type weatherType) {
        this.weatherType = weatherType;
    }

    public int getTerrainTurns() {
        return terrainTurns;
    }

    public void setTerrainTurns(int terrainTurns) {
        this.terrainTurns = terrainTurns;
    }

    public Type getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(Type terrainType) {
        this.terrainType = terrainType;
    }
}
