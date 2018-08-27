package com.publicis.sapient.iot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicis.sapient.iot.domain.NeighbourhoodSimulationRequest;
import com.publicis.sapient.iot.service.MockSimulationLoadFetcher;
import com.publicis.sapient.iot.service.SimulationLoadFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class SimulationController {

    Map<String, SimulationLoadFetcher> simulations = new HashMap<>();

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(value = "/startNeighbourhoodSimulation", method = RequestMethod.POST)
    public ResponseEntity<String> startNeighbourhoodSimulation(@RequestBody NeighbourhoodSimulationRequest request) throws Exception {
        startSimulation(request);
        return ResponseEntity.ok().build();
    }

    @MessageMapping("/stopNeighbourhoodSimulation")
    public ResponseEntity<String> stopNeighbourhoodSimulation(String uuid) throws Exception {
        stopSimulation(uuid);
        return ResponseEntity.ok().build();
    }

    private void startSimulation(NeighbourhoodSimulationRequest request){
        SimulationLoadFetcher simulationLoadFetcher = new MockSimulationLoadFetcher(request, template, objectMapper);
        simulations.put(request.getId(), simulationLoadFetcher);
        Thread thread = new Thread(simulationLoadFetcher);
        thread.start();
    }

    private void stopSimulation(String uuid) {
        simulations.get(uuid).stop();
    }
}
