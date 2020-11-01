package ru.sanikitin.spark.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.requests.OffsetCommitRequest;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.cassandra.Off;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.apache.spark.streaming.kafka010.OffsetRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sanikitin.spark.DTO.LogDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KafkaRX {
    private final JavaSparkContext sparkContext;
    private final Map<String, Object> kafkaParams = new HashMap<>();
    private final List<OffsetRange> offsetRangesList = new ArrayList<>();
    private static final String TOPICS = "hw2";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";

    /**
     * Kafka Consumer constructor
     * @param sparkContext Context of job
     * @return KafkaConsumer
     */
    @Autowired
    public KafkaRX(JavaSparkContext sparkContext) {
        this.sparkContext = sparkContext;
        kafkaParams.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", LogDeserializer.class);
        kafkaParams.put("group.id", "id");
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        offsetRangesList.add(OffsetRange.create(TOPICS, 0, 0, 10000));

    }

    /**
     * Recieve rows from Kafka
     */
    public JavaRDD<LogDTO> read(){
        OffsetRange[] ranges = new OffsetRange[offsetRangesList.size()];
        offsetRangesList.toArray(ranges);
        //KafkaUtils.createRDD(sparkContext, kafkaParams, )

        JavaRDD<ConsumerRecord<String, LogDTO>> rdd = KafkaUtils.createRDD(
                sparkContext,
                kafkaParams,
                ranges,
                LocationStrategies.PreferConsistent()
        );
        return rdd.map(ConsumerRecord::value);
    }
}
