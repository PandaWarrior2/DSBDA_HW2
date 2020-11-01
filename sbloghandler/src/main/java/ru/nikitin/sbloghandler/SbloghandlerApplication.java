package ru.nikitin.sbloghandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SbloghandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbloghandlerApplication.class, args);
    }

    /*@Scheduled(fixedDelay = 1000)
    public void print() {
        log.info("hello, world");
    }*/
}
