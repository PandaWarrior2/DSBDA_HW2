package ru.nikitin.sbloghandler.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.nikitin.sbloghandler.dto.LogDTO;

import java.util.List;
import java.util.Map;

//@Component
public class KafkaPartitioner implements Partitioner {
    private static Logger log = LoggerFactory.getLogger(KafkaPartitioner.class);

    /**
     * Returns the partition number for a given record
     * @param topic Name of the topic
     * @param key Key of the kafka record
     * @param value Value of the kafka record
     * @param cluster Kafka cluster metadata
     * @return Partition number
     */
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numberOfPartitions = 7;//partitionInfos.size();
        System.out.println(numberOfPartitions);
        LogDTO log = (LogDTO) value;
        return log.getPriority();
        //return metric.getId().intValue() & Integer.MAX_VALUE % numberOfPartitions;
    }

    /**
     * This is called when partitioner is closed.
     */
    @Override
    public void close() {
    }

    /**
     * Configure this class with the given key-value pairs
     */
    @Override
    public void configure(Map configs) {
    }

    /**
     * Notifies the partitioner a new batch is about to be created
     * @param topic Name of the topic
     * @param cluster Kafka cluster metadata
     * @param prevPartition The partition previously selected for the record that triggered a new batch
     */
    @Override
    public void onNewBatch(String topic, Cluster cluster, int prevPartition) {
    }
}
