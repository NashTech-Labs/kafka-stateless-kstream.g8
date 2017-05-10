package com.knoldus.kstreams;


import com.knoldus.utils.ConfigReader;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashTag {

    private ConfigReader configReader = new ConfigReader();

    public void getHashTags() {
        Properties streamsConfiguration = new Properties();
        streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "get-all-hashtags");
        streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        streamsConfiguration.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        streamsConfiguration.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        /**
         * hashTagTopic: topic in which processed result is stored
         */
        String hashTagTopic = configReader.getKStreamTopic();
        /**
         * feedsTopic topic from which twitter feeds will be fetched.
         */
        String feedsTopic = configReader.getKafkaTopic();

        final Serde<String> stringSerde = Serdes.String();

        KStreamBuilder builder = new KStreamBuilder();
        KStream<String, String> feeds = builder.stream(stringSerde, stringSerde, feedsTopic);

        KStream<String, String> hashTags = feeds.mapValues(HashTag::getHashTags)
                .map((key, value) -> new KeyValue<>(key, value.toString()));

        hashTags.to(stringSerde, stringSerde, hashTagTopic);

        KafkaStreams streams = new KafkaStreams(builder, streamsConfiguration);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private static List<String> getHashTags(final String twitterFeeds) {
        Pattern regExPattern = Pattern.compile("#(\\w+)");
        List<String> hashTags = new ArrayList<>();
        Matcher mat = regExPattern.matcher(twitterFeeds);
        while (mat.find()) {
            hashTags.add(mat.group());
        }
        return hashTags;
    }
}

