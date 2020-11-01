package ru.nikitin.sbloghandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import ru.nikitin.sbloghandler.dto.LogDTO;

import java.io.File;
import java.util.List;

@Service
@Profile("!test")
public class KafkaListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private LogParser csvService;

    @Autowired
    private KafkaTX kafkaSender;

    /**
     * Parse source csv and send metrics to kafka after application context has been initialized
     * @param contextRefreshedEvent Event of initializing application context
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        File logFile = new File("/home/sanikitin/DSBDA/messages");
        List<LogDTO> rowList = csvService.parseLog(logFile);
        //rowList.forEach(System.out::println);
        rowList.forEach(row -> kafkaSender.send(row));
    }
}