# Testing Kafka Cluster

## Produce and Consume Messagge
- bin/kafka-topics.sh --zookeeper zookeeper1:2181,zookeeper2:2181,zookeeper3:2181/kafka --create --topic first_topic --replication-factor 2 --partitions 2

- bin/kafka-topics.sh --zookeeper zookeeper1:2181,zookeeper2:2181,zookeeper3:2181/kafka --list

- bin/kafka-console-producer.sh --broker-list kafka1:9092,kafka2:9092,kafka3:9092 --topic first_topic 

- bin/kafka-console-consumer.sh --bootstrap-server kafka1:9092,kafka2:9092,kafka3:9092 --topic first_topic --from-beginning (try this consumer command from another instance)

# Produce Message Continously

- Create File
    - base64 /dev/urandom | head -c 10000 | egrep -ao "\w" | tr -d '\n' > testfile.txt

- Produce the message
    -   bin/kafka-producer-perf-test.sh --topic first_topic --num-records 10000 --throughput 10 --payload-file testfile.txt --producer-props acks=1 bootstrap.servers=kafka1:9092,kafka3:9092 --payload-delimiter A

- Consume Message
    -  bin/kafka-console-consumer.sh --bootstrap-server kafka1:9092,kafka3:9092 --topic first_topic
