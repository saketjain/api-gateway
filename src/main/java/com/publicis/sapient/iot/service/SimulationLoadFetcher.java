package com.publicis.sapient.iot.service;

import com.publicis.sapient.iot.domain.NeighbourhoodSimulationRequest;

public interface SimulationLoadFetcher extends Runnable{

    //Start new or paused simuation
    public void start();

    //Stop running simulation.
    public void stop();

    //Pause running simulation
    public void pause();

    //Update Neighbourhood Simulation Request
    public void update(NeighbourhoodSimulationRequest request);

    public enum Status {
        RUNNING,
        STOPPED,
        PAUSED;
    }
}
