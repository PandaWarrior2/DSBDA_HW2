package ru.sanikitin.spark.kafka;

import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.sanikitin.spark.DTO.LogDTO;

import java.io.IOException;

@Service
public class LogDeserializer implements Deserializer<LogDTO> {
    /**
     * Deserialize value of kafka value to LogDTO
     * @param s Name of the kafka topic
     * @param bytes kafka record values bytes
     * @return System log DTO
     */
    @Override
    public LogDTO deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        LogDTO log = null;
        try{
            log = objectMapper.readValue(bytes, LogDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log;
    }
}
