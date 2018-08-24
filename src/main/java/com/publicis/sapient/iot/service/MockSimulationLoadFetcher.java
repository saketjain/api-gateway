package com.publicis.sapient.iot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.iot.domain.Load;
import com.publicis.sapient.iot.domain.NeighbourhoodSimulationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MockSimulationLoadFetcher implements SimulationLoadFetcher{

    private final int SECONDS_IN_A_DAY = 24 * 3600;

    private NeighbourhoodSimulationRequest neighbourhoodSimulationRequest;

    private String topic = "";

    private SimpMessagingTemplate template;

    private SimulationLoadFetcher.Status status;

    private ObjectMapper objectMapper;


    public MockSimulationLoadFetcher(
            NeighbourhoodSimulationRequest neighbourhoodSimulationRequest,
            SimpMessagingTemplate template,
            ObjectMapper objectMapper
    ) {
        this.neighbourhoodSimulationRequest = neighbourhoodSimulationRequest;
        this.topic = "/topic/" + neighbourhoodSimulationRequest.getUuid();
        this.template = template;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run() {
        int frequency = this.neighbourhoodSimulationRequest.getSimulationSpeedInSeconds();
        int numberOfDataPoints = Math.round(SECONDS_IN_A_DAY/frequency);
        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        for(int i = 0; i < numberOfDataPoints; i ++){
            Load load = new Load(time.plusSeconds(frequency), Math.abs((long)Math.random() * 100));
            log(load);
            template.convertAndSend(topic, load);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(Load load) {
        try {
            System.out.println("publishing load " + objectMapper.writeValueAsString(load) +  " to topic: " + topic);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(NeighbourhoodSimulationRequest request) {
        this.neighbourhoodSimulationRequest = request;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
    }

    @Override
    public void pause() {

    }

}
