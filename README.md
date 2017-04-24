# Kafka Stateless Stream Processing

This is an activator project showcasing stateless stream processing using Kafka KStream by fetching all the hashTags in your tweets.

---
### Steps to install Zookeeper and Apache Kafka:

Step 1: Download Kafka

Download Kafka from [here](https://www.apache.org/dyn/closer.cgi?path=/kafka/0.10.1.1/kafka_2.11-0.10.1.1.tgz)

Step 2: Extract downloaded file

```bash
tar -xzvf kafka_2.11-0.10.1.1.tgz
cd kafka_2.11-0.10.1.1
```
### Steps to start Zookeeper and Kafka server :

Start Zookeeper:

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

Start Kafka server:

```bash
bin/kafka-server-start.sh config/server.properties
```


---
### Clone Project

```bash
git clone git@github.com:knoldus/activator-stateful-kstream-kafka.git
cd activator-kstream-kafka
bin/activator clean compile
```
---
### Start producing tweets from your twitter account into a kafka topic

Step 1: Add your twitter authentication tokens in application.conf.

Step 2:
Execute the following command,

```bash
bin/activator "run-main com.knoldus.demo.TweetProducer"
```
This starts fetching tweets and push each of it into a Kafka topic queue.

---
### Start processing these tweets using,

Step 1:
Execute the following command,

```bash
bin/activator "run-main com.knoldus.demo.KStreamDemo"
```

This begins stream processing on the kafka topic which contains your tweets.

### Start consuming all the hash Tags in each tweet,

```bash
bin/activator "run-main com.knoldus.demo.ConsumerDemo"
```

---
For any issue please raise a ticket @ [Github Issue](https://github.com/knoldus/kafka-stateless-kstream/issues)