package ru.nikitin.sbloghandler.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties приложения для работы с кассанрдрой
 * */
@ConfigurationProperties(prefix = "cassandra")
@Data
public class CassandraAppProperties {
    private String connectionHost;
    private String keySpace;
    private String table;
}
