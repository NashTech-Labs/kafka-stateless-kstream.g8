package com.knoldus.kstreams;

import com.knoldus.utils.ConfigReader;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {
    private ConfigReader configReader = new ConfigReader();

    public void consume(String groupId, String kafkaTopic) {
        String kafkaServers = configReader.getKafkaServers();
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        org.apache.kafka.clients.consumer.KafkaConsumer<String, Long> kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        kafkaConsumer.subscribe((Collections.singletonList(kafkaTopic)));
        do {
            ConsumerRecords<String, Long> records = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, Long> record : records) {
                System.out.println("HashTags received:" + record.value());
            }
        } while (true);
    }
}
