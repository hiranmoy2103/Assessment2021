package com.emirates.test.flightdetailsreactive.repository;

import com.emirates.test.flightdetailsreactive.model.Flight;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class CustomFlightRepoImpl implements CustomFlightRepo {

    private final ReactiveMongoTemplate mongoTemplate;

    public CustomFlightRepoImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Flux<Flight> getFlightInfo(String date, String arrCode, String depCode) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("flightDate").is(date),
                        Criteria.where("arrCode").is(arrCode),
                        Criteria.where("depCode").is(depCode)
                )
        );
        return mongoTemplate.find(query, Flight.class);
    }

    @Override
    public Mono<Double> getFlightPrice(String flightNumber, String date) {
        Query query = new Query();
        query.addCriteria(
                new Criteria().andOperator(
                        Criteria.where("flightNumber").is(flightNumber),
                        Criteria.where("flightDate").is(date)
                )
        );
        Flux<Flight> flightDetails = mongoTemplate.find(query, Flight.class);
        return flightDetails.take(1).single().map(s -> s.getBasePrice());
    }
}
