package com.emirates.test.flightdetailsreactive.service;

import com.emirates.test.flightdetailsreactive.client.PostClientImpl;
import com.emirates.test.flightdetailsreactive.model.Flight;
import com.emirates.test.flightdetailsreactive.model.FlightSaveRequest;
import com.emirates.test.flightdetailsreactive.model.Post;
import com.emirates.test.flightdetailsreactive.repository.FlightRepository;
import com.emirates.test.flightdetailsreactive.rule.PricingRule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class FlightService {

    private FlightRepository flightRepository;

    private PostClientImpl client;

    private PricingRule pricingRule;

    public FlightService(FlightRepository flightRepository, PostClientImpl client,PricingRule pricingRule) {
        this.flightRepository = flightRepository;
        this.client=client;
        this.pricingRule=pricingRule;

    }


    public Flux<Flight> getFlightInfo(String date, String arrCode, String depCode) {
        Instant start = Instant.now();
        //Calling 5 downstream systems.
        downStreamServices();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");
        return flightRepository.getFlightInfo(date, arrCode, depCode);
    }


    public Mono<Double> getFlightPrice(String flightNumber, String date) {
        Mono<Double> flightBasePrice=flightRepository.getFlightPrice(flightNumber, date);
        return PricingRule.pricingRules(flightNumber,date,flightBasePrice);
    }

    public Mono<Flight> saveFlightDetails(FlightSaveRequest flightSaveRequest) {
        Flight flightData = new Flight();
        flightSaveRequest.setRouteRegistrationNo(UUID.randomUUID().toString());
        BeanUtils.copyProperties(flightSaveRequest,flightData);
        return flightRepository.save(flightData);
    }

    public void downStreamServices() {
        Post post = new Post("userId", "91", "FlightTest", "Flights are running late due to dense fog");
        client.create(post);
        client.findAll();
        client.findById("20");
        client.remove("13");
        client.findById("15");
       /* try {
            //extra 2 seconds additional delay.
            Thread.sleep(2 * 1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }*/
    }


}
