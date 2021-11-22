package com.emirates.test.flightdetailsreactive.repository;

import com.emirates.test.flightdetailsreactive.model.Flight;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String>, CustomFlightRepo {
}
