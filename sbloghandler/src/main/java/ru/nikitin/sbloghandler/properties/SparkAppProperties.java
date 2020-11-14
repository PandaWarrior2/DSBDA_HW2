package ru.nikitin.sbloghandler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties приложения для работы со спарком
 * */
@ConfigurationProperties(prefix = "spark")
@Data
public class SparkAppProperties {
    private Integer streamingDuration;
    private String master;
    private String executorMemory;
    private String driverMemory;
    private String driverMaxResultSize;
    private String appName;
}
