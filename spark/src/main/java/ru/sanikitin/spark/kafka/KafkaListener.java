package ru.sanikitin.spark.kafka;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.sanikitin.spark.DTO.AggregatedLogDTO;
import ru.sanikitin.spark.DTO.LogDTO;
import ru.sanikitin.spark.service.CassandraService;
import ru.sanikitin.spark.service.KafkaConsumerService;
import ru.sanikitin.spark.service.LogHandleService;
import sun.awt.X11.XSystemTrayPeer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
//@Profile("!test")
public class KafkaListener implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * Parse source csv and send metrics to kafka after application context has been initialized
     * @param contextRefreshedEvent Event of initializing application context
     */
    @Autowired
    JavaSparkContext javaSparkContext;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        /*SparkConf conf = new SparkConf()
                .set("spark.cassandra.connection.host", "127.0.0.1")
                .setAppName("Spark RDD metric aggregator")
                .setMaster("local[2]");

        JavaSparkContext sparkContext = new JavaSparkContext(conf);*/

        CassandraService CS = new CassandraService(javaSparkContext);
        KafkaRX kafkaConsumer = new KafkaRX(javaSparkContext);
        KafkaConsumerService kcs = new KafkaConsumerService();
        List<LogDTO> loglist = kcs.getDTO();
        loglist.forEach(System.out::println);
        JavaRDD<LogDTO> logRDD = kafkaConsumer.read();
        CS.writeLog(logRDD);

        //JavaRDD<AggregatedLogDTO> aggregatedMetricRdd = LogHandleService.aggregateRows(metricRdd);
    }
}
