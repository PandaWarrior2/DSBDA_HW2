package ru.sanikitin.spark.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.sanikitin.spark.DTO.LogDTO;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Service
public class KafkaConsumerService{

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);
    private List<LogDTO> logs;
    @KafkaListener(topics = "${app.topic.foo}")
    public void read(@Payload String msg){
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(msg);
        try {
            LogDTO dto = mapper.readValue(reader, LogDTO.class);
            logs.add(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<LogDTO> getDTO(){
        return logs;
    }
    /*public void listen(@Payload String message) {
        LOG.info("received message='{}'", message);
    }*/


}
