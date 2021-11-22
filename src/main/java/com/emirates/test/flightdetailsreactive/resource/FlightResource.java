package com.emirates.test.flightdetailsreactive.resource;

import com.emirates.test.flightdetailsreactive.model.Flight;
import com.emirates.test.flightdetailsreactive.model.FlightSaveRequest;
import com.emirates.test.flightdetailsreactive.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@RestController
public class FlightResource {

    private FlightService fightService;

    public FlightResource(FlightService fightService) {
        this.fightService = fightService;
    }

    private ResponseEntity notFound = ResponseEntity.notFound().build();

    @ExceptionHandler
    public ResponseEntity<String> handle(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @GetMapping("/flight")
    public Flux<Flight> getFlightInfo(@RequestParam("date") String date,
                                      @RequestParam("arrCode") String arrCode,
                                      @RequestParam("depCode") String depCode) {
        return fightService.getFlightInfo(date, arrCode, depCode);
    }

    @GetMapping("/price")
    public Mono<Double> getFlightPrice(@RequestParam("flightNo") String flightNo,
                                       @RequestParam("flightDate") String flightDate) {
        return fightService.getFlightPrice(flightNo, flightDate);
    }

    @PostMapping(value = "/flight",consumes="application/json" ,produces = "application/json")
    public Mono<Flight> saveFlightDetails(@NotNull @NotEmpty @RequestBody FlightSaveRequest flightSaveRequest) {
        if (flightSaveRequest == null) {
            throw new RuntimeException("Request object is null!!");
        }
        return fightService.saveFlightDetails(flightSaveRequest);
    }


}
