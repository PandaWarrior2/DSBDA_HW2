package ru.sanikitin.spark.service;
import org.apache.kafka.common.utils.SystemScheduler;
import org.apache.spark.api.java.JavaRDD;
import ru.sanikitin.spark.DTO.AggregatedLogDTO;
import ru.sanikitin.spark.DTO.LogDTO;

import java.util.List;
//import scala.Tuple2;

public class LogHandleService {
    public static JavaRDD<AggregatedLogDTO> aggregateRows(JavaRDD<LogDTO> log_rdd){
        List<LogDTO> lst = log_rdd.collect();
        for(LogDTO i: lst){
            System.out.println(i.getMessage());
        }
        return log_rdd.map(i -> new AggregatedLogDTO(i.getDatetime(), i.getPriority().toString(), 1));
    }
}
