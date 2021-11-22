package com.emirates.test.flightdetailsreactive.service;

import com.emirates.test.flightdetailsreactive.client.PostClientImpl;
import com.emirates.test.flightdetailsreactive.model.Flight;
import com.emirates.test.flightdetailsreactive.repository.FlightRepository;
import com.emirates.test.flightdetailsreactive.rule.PricingRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FlightServicelTest {

    @InjectMocks
    private FlightService flightService;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private PostClientImpl client;
    @Mock
    private PricingRule pricingRule;


    @Test
    void testServiceGetFlightInfo() {
        Flight flight = new Flight("AI2121", "2021-11-22", 5000, "MAA", "DXB", UUID.randomUUID().toString());
        Flux<Flight> source = Flux.just(flight);
        when(flightRepository.getFlightInfo(anyString(), anyString(), anyString())).thenReturn(source);
        StepVerifier.create(flightService.getFlightInfo("2021-11-22", "DXB", "MAA"))
                .assertNext(f -> assertThat(f)
                        .isNotNull()
                        .hasFieldOrPropertyWithValue("flightNumber", flight.getFlightNumber())
                        .hasFieldOrPropertyWithValue("flightDate", flight.getFlightDate())
                        .hasFieldOrPropertyWithValue("basePrice", flight.getBasePrice())
                        .hasFieldOrPropertyWithValue("depCode", flight.getDepCode())
                        .hasFieldOrPropertyWithValue("arrCode", flight.getArrCode()))
                .verifyComplete();
    }

    @Test
    void testServiceGetFlightPrice() {
        Flight flight = new Flight("AI2121", "2021-11-22", 5000, "MAA", "DXB", UUID.randomUUID().toString());
        Mono<Double> source = Mono.just(5000.00);
        when(flightRepository.getFlightPrice("AI2121", "2021-11-22")).thenReturn(source);
        StepVerifier.create(flightService.getFlightPrice("AI2121", "2021-11-22"))
                .assertNext(f -> assertThat(f)
                        .isNotNull().isEqualTo(4250.00)) //After Weekly discount applied
                .verifyComplete();
    }
}