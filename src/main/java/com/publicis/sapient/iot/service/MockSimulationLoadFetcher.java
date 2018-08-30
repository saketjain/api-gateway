package com.publicis.sapient.iot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.iot.domain.Load;
import com.publicis.sapient.iot.domain.NeighbourhoodSimulationRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.*;

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
        this.topic = "/topic/" + neighbourhoodSimulationRequest.getId();
        this.template = template;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run() {
        int frequency = this.neighbourhoodSimulationRequest.getClockRate();
        int numberOfDataPoints = Math.round(SECONDS_IN_A_DAY/frequency);
        LocalDateTime time = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        for(int i = 0; i < numberOfDataPoints; i ++){
            long millis = time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Load load = new Load(millis, Math.abs((long)(Math.random() * 100)));
            log(load);
            template.convertAndSend(topic, load);
            time = time.plusSeconds(frequency);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(Load load) {
        try {
            LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(load.getTimeStamp()), ZoneId.systemDefault());
            System.out.println("publishing load " + time +  " to topic: " + topic);
        } catch (Exception e) {
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
