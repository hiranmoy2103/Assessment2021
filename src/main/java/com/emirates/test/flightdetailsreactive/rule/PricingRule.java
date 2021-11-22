package com.emirates.test.flightdetailsreactive.rule;

import com.emirates.test.flightdetailsreactive.utility.Utility;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PricingRule {
    public PricingRule() {
    }

    public static Mono<Double> pricingRules(String flightNumber, String flightDate, Mono<Double> basePrice) {
        if (Utility.isChristmasHoliDay(flightDate)) {
            return basePrice.map(val -> val * 1.5);
        }
        if (Utility.isWeekend(flightDate)) {
            return basePrice.map(val -> val * 1.25);
        } else {
            return basePrice.map(val -> val * 0.85);
        }

    }
}
