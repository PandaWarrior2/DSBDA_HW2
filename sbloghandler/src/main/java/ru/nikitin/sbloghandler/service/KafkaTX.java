package ru.nikitin.sbloghandler.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import ru.nikitin.sbloghandler.dto.LogDTO;


@Service
public class KafkaTX {

    private static final Logger logger = LoggerFactory.getLogger(KafkaTX.class);

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, LogDTO> kafkaTemplate;

    /**
     * Send metric to kafka
     * @param rowList Rows from log for sending to Kafka Producer.
     * @return
     */
    public ListenableFuture<SendResult<String, LogDTO>> send(LogDTO rowList) {
        logger.info("sending testDataDto='{}'", rowList.toString());
        return kafkaTemplate.send(topic, rowList);
    }
}
