package com.nab.ltc.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * @author Tushar Sisode
 *
 */
@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class LtcApplication extends SpringBootServletInitializer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LtcApplication.class, args);
    }
}