package com.emirates.test.flightdetailsreactive;

import com.emirates.test.flightdetailsreactive.model.Flight;
import com.emirates.test.flightdetailsreactive.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class FlightInfoReactiveApplication {

    @Bean
    CommandLineRunner flightData(FlightRepository fightRepository) {
        return args -> {
            fightRepository
                    .deleteAll()
                    .subscribe(null, null, () -> {

                        Stream.of(new Flight("AI2123", "2021-11-21", 5000, "CCU", "DXB", UUID.randomUUID().toString()),
                                        new Flight("AI2123", "2021-11-22", 5000, "MAA", "DXB", UUID.randomUUID().toString()),
                                        new Flight("EA2873", "2021-11-22", 5900, "MAA", "DXB", UUID.randomUUID().toString()),
                                        new Flight("BA4123", "2021-11-23", 7000, "PUN", "DXB", UUID.randomUUID().toString()),
                                        new Flight("AI3399", "2021-11-24", 9000, "DXB", "QXT", UUID.randomUUID().toString()),
                                        new Flight("QA9121", "2021-12-02", 7800, "BOM", "COL", UUID.randomUUID().toString()),
                                        new Flight("EA5526", "2021-12-05", 7300, "LHR", "GEN", UUID.randomUUID().toString()),
                                        new Flight("OM9127", "2021-12-27", 6500, "DCA", "MAL", UUID.randomUUID().toString()),
                                        new Flight("AI0012", "2021-12-08", 9800, "GAT", "DXB", UUID.randomUUID().toString()),
                                        new Flight("AI3399", "2021-12-08", 9800, "DXB", "QXT", UUID.randomUUID().toString()))
                                .forEach(flight -> {
                                    fightRepository.save(flight)
                                            .subscribe(System.out::println);
                                });

                    })
            ;
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(FlightInfoReactiveApplication.class, args);
    }
}

