package com.emirates.test.flightdetailsreactive.resource;

import com.emirates.test.flightdetailsreactive.model.Flight;
import com.emirates.test.flightdetailsreactive.model.FlightSaveRequest;
import com.emirates.test.flightdetailsreactive.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FlightResource.class})
@WebFluxTest
class FlightResourceTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private FlightService service;


    @Test
    void testGetFlightInfo() {
        Flight flight = new Flight("AI2123", "2021-11-22", 5000, "MAA", "DXB", UUID.randomUUID().toString());
        Flux<Flight> source = Flux.just(flight);
        when(service.getFlightInfo(anyString(), anyString(), anyString())).thenReturn(source);
        client.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/flight")
                                .queryParam("date", "2021-11-30")
                                .queryParam("arrCode", "CAP")
                                .queryParam("depCode", "DXB")
                                .build()).exchange().expectStatus().isOk();

    }


    @Test
    void testGetPrice() {
        Flight flight = new Flight("AI2121", "2021-11-22", 5000, "MAA", "DXB", UUID.randomUUID().toString());
        Mockito
                .when(service.getFlightPrice(anyString(), anyString()))
                .thenReturn(Mono.just(flight.getBasePrice()));

        client.get().uri(uriBuilder ->
                uriBuilder
                        .path("/price")
                        .queryParam("flightNo", "AI2121")
                        .queryParam("flightDate", "2021-11-22")
                        .build()).exchange().expectStatus().isOk().expectBody(Double.class).value(p -> assertThat(p).isPositive());
    }

    @Test
    void testSaveFlightDetails() {
        Flight flight = new Flight("AI2121", "2021-11-22", 5000, "MAA", "DXB", UUID.randomUUID().toString());
        Mono<Flight> source = Mono.just(flight);
        when(service.saveFlightDetails(any(FlightSaveRequest.class))).thenReturn(source);
        client.post()
                .uri("/flight")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(flight))
                .exchange().expectBody(Flight.class).value(p -> assertThat(p)
                        .hasFieldOrPropertyWithValue("flightNumber", flight.getFlightNumber())
                        .hasFieldOrPropertyWithValue("flightDate", flight.getFlightDate())
                        .hasFieldOrPropertyWithValue("basePrice", flight.getBasePrice())
                        .hasFieldOrPropertyWithValue("depCode", flight.getDepCode())
                        .hasFieldOrPropertyWithValue("arrCode", flight.getArrCode())
                );
    }

}