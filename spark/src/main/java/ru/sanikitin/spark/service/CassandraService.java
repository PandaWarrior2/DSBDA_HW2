package ru.sanikitin.spark.service;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.datastax.spark.connector.japi.RDDAndDStreamCommonJavaFunctions;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sanikitin.spark.DTO.AggregatedLogDTO;
import ru.sanikitin.spark.DTO.LogDTO;

import javax.xml.ws.ServiceMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class CassandraService {
    JavaSparkContext javaSparkContext;
    @Autowired
    public CassandraService(JavaSparkContext javaSparkContext){
        this.javaSparkContext = javaSparkContext;
        CassandraConnector connect = CassandraConnector.apply(javaSparkContext.getConf());
        try(CqlSession ss = connect.openSession()){
            ss.execute("CREATE KEYSPACE IF NOT EXISTS hw2 WITH REPLICATION  = {'class': 'SimpleStrategy', 'replication_factor': 1}");
            /*ss.execute("CREATE TABLE IF NOT EXISTS hw2.syslog (" +
                    "datetime timestamp," +
                    "hostname varchar," +
                    "process varchar," +
                    "message varchar," +
                    "priority int," +
                    "PRIMARY KEY((priority), datetime, hostname, process, message))");*/
            ss.execute("CREATE TABLE IF NOT EXISTS hw2.syslog " +
                    "(id bigint, datetime timestamp, hostname text, process text, message text, priority int," +
                    "PRIMARY KEY ((id), priority));");

            ss.execute("CREATE TABLE IF NOT EXISTS hw2.result (" +
                    "    create_dttm timestamp," +
                    "    priority int," +
                    "    count int," +
                    "    PRIMARY KEY ((priority), create_dttm)" +
                    ")");
        }
    }

    public void writeLog(JavaRDD<LogDTO> RDD) {
        CassandraJavaUtil.javaFunctions(RDD).saveToCassandra(
                "hw2",
                "syslog",
                CassandraJavaUtil.mapToRow(LogDTO.class),
                CassandraJavaUtil.someColumns( "id", "datetime", "hostname", "message", "priority", "process"));
        /*List<LogDTO> input = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd HH:mm:ss", Locale.US);
        try {
            input.add(new LogDTO(1, df.parse("Mar 17 01:00:00"), "hname", "proc", "msg", 1));
            input.add(new LogDTO(2, df.parse("Mar 17 01:00:00"), "hname", "proc", "msg", 1));
            input.add(new LogDTO(3, df.parse("Mar 17 01:00:00"), "hname", "proc", "msg", 1));
        }catch (ParseException e) {
            e.printStackTrace();
        }
        JavaRDD<LogDTO> logRDD = javaSparkContext.parallelize(input, 2);
        CassandraJavaUtil.javaFunctions(logRDD).saveToCassandra(
                "hw2",
                "syslog",
                CassandraJavaUtil.mapToRow(LogDTO.class),
                CassandraJavaUtil.someColumns( "id", "datetime", "hostname", "message", "priority", "process"));

         */
    }
}
