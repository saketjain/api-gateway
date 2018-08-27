package com.publicis.sapient.iot.domain;

import java.time.LocalDateTime;

public class NeighbourhoodSimulationRequest {

    private String id;

    private int numberOfHouses;

    //Intensity of sunlight
    private int sunlight;

    //For e.g. simulationSpeed=3600
    //means 1 simulation sec = 1 real hour
    private int clockRate;

    //Seconds elapsed from start of day
    private LocalDateTime currentTimeStamp;

    public NeighbourhoodSimulationRequest(){

    }

    public NeighbourhoodSimulationRequest(String id, int numberOfHouses, int sunlight, int clockRate, LocalDateTime currentTimeStamp) {
        this.id = id;
        this.numberOfHouses = numberOfHouses;
        this.sunlight = sunlight;
        this.clockRate = clockRate;
        this.currentTimeStamp = currentTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getClockRate() {
        return clockRate;
    }

    public void setClockRate(int clockRate) {
        this.clockRate = clockRate;
    }

    public LocalDateTime getCurrentTimeStamp() {
        return currentTimeStamp;
    }

    public void setCurrentTimeStamp(LocalDateTime currentTimeStamp) {
        this.currentTimeStamp = currentTimeStamp;
    }
}
