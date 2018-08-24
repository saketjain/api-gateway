package com.publicis.sapient.iot.domain;

import java.time.LocalDateTime;

public class NeighbourhoodSimulationRequest {

    private String uuid;

    private int numberOfHouses;

    //Intensity of sunlight
    private int sunlight;

    //For e.g. simulationSpeed=3600
    //means 1 simulation sec = 1 real hour
    private int simulationSpeedInSeconds;

    //Seconds elapsed from start of day
    private LocalDateTime simulationStartTime;

    public NeighbourhoodSimulationRequest(){

    }

    public NeighbourhoodSimulationRequest(String uuid, int numberOfHouses, int sunlight, int simulationSpeedInSeconds, LocalDateTime simulationStartTime) {
        this.uuid = uuid;
        this.numberOfHouses = numberOfHouses;
        this.sunlight = sunlight;
        this.simulationSpeedInSeconds = simulationSpeedInSeconds;
        this.simulationStartTime = simulationStartTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getNumberOfHouses() {
        return numberOfHouses;
    }

    public void setNumberOfHouses(int numberOfHouses) {
        this.numberOfHouses = numberOfHouses;
    }

    public int getSunlight() {
        return sunlight;
    }

    public void setSunlight(int sunlight) {
        this.sunlight = sunlight;
    }

    public int getSimulationSpeedInSeconds() {
        return simulationSpeedInSeconds;
    }

    public void setSimulationSpeedInSeconds(int simulationSpeedInSeconds) {
        this.simulationSpeedInSeconds = simulationSpeedInSeconds;
    }

    public LocalDateTime getSimulationStartTime() {
        return simulationStartTime;
    }

    public void setSimulationStartTime(LocalDateTime simulationStartTime) {
        this.simulationStartTime = simulationStartTime;
    }
}
