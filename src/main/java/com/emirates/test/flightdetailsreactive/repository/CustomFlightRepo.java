package com.emirates.test.flightdetailsreactive.repository;

import com.emirates.test.flightdetailsreactive.model.Flight;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomFlightRepo {
    Flux<Flight> getFlightInfo(String date, String arrCode, String depCode);

    Mono<Double> getFlightPrice(String flightNumber, String date);
}
