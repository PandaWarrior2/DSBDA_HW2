package ru.nikitin.sbloghandler.config;

import lombok.RequiredArgsConstructor;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nikitin.sbloghandler.properties.CassandraAppProperties;
import ru.nikitin.sbloghandler.properties.SparkAppProperties;

/**
 * Конфигурация работы спарка и бин создания spark streaming context для чтения данных из kafka
 * */
@Configuration
@RequiredArgsConstructor
public class SparkAppConfig {

    private final SparkAppProperties sparkProperties;
    private final CassandraAppProperties cassandraProperties;

    /**
     * Бин конфигурации спарка*/
    @Bean
    public SparkConf sparkConf() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName(sparkProperties.getAppName());
        sparkConf.set("spark.cassandra.connection.host", cassandraProperties.getConnectionHost());
        sparkConf.setMaster(sparkProperties.getMaster());
        sparkConf.set("spark.executor.memory", sparkProperties.getExecutorMemory());
        sparkConf.set("spark.driver.memory", sparkProperties.getDriverMemory());
        sparkConf.set("spark.driver.maxResultSize", sparkProperties.getDriverMaxResultSize());
        return sparkConf;
    }

    /**
     * Бин spark streaming context для чтения данных из kafka */
    @Bean
    public JavaStreamingContext javaStreamingContext(SparkConf sparkConf) {
        JavaSparkContext sc = new JavaSparkContext(SparkContext.getOrCreate(sparkConf));
        return new JavaStreamingContext(sc, Durations.seconds(sparkProperties.getStreamingDuration()));
    }

}
