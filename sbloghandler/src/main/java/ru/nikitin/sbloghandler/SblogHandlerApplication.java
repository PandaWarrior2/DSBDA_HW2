package ru.nikitin.sbloghandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.nikitin.sbloghandler.properties.CassandraAppProperties;
import ru.nikitin.sbloghandler.properties.KafkaAppProperties;
import ru.nikitin.sbloghandler.properties.SparkAppProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {SparkAppProperties.class, KafkaAppProperties.class, CassandraAppProperties.class})
@Slf4j
public class SblogHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SblogHandlerApplication.class, args);
    }

}
