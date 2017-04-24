package com.knoldus.demo;

import com.knoldus.kstreams.KafkaConsumer;
import com.knoldus.utils.ConfigReader;

public class KStreamConsumer {

    public static void main(String[] args) {
        ConfigReader configReader = new ConfigReader();
        String kafkaTopic = configReader.getKStreamTopic();
        String kafkaGroupId = "my-group";
        System.out.println("Kafka Topics: " + kafkaTopic);
        System.out.println("Kafka Group ID: " + kafkaGroupId);
        new KafkaConsumer().consume(kafkaGroupId, kafkaTopic);
    }

}
