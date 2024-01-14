package com.example.gateway;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LoanFallbackController {

    private static final Logger log = LoggerFactory.getLogger(LoanFallbackController.class);

    @Observed
    @GetMapping("/loan-fallback")
    Mono<String> getBooksFallback() {
        log.info("Fallback for loan service");
        return Mono.just("Loan Service Unreachable .... Response From Fallback.....");
    }
}
