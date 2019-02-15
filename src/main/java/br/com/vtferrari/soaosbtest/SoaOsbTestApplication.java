package br.com.vtferrari.soaosbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableFeignClients
@SpringBootApplication
public class SoaOsbTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaOsbTestApplication.class, args);
    }

}

