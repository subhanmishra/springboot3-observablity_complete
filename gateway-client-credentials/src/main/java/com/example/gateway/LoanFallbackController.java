package com.example.gateway;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/loan-fallback")
@Observed
public class LoanFallbackController {

    private static final Logger log = LoggerFactory.getLogger(LoanFallbackController.class);


    @GetMapping
    Mono<String> getLoansFallback() {
        log.info("Fallback for Get loan service");
        return Mono.just("Loan Service Unreachable .... Response From Fallback GET.....");
    }


    @PostMapping
    Mono<String> applyLoansFallback() {
        log.info("Fallback for Apply loan service");
        return Mono.just("Loan Service Unreachable .... Response From Fallback POST.....");
    }
}
