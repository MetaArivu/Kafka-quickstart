# Kafka Monitoring Tools

Below tools are available in market
- Prometheus + Grafana
    - Prometheus - Gets and Store Metrics
    - Grafana - Create Dashboard based on prometheus
- ElasticSearch + Kibana (ELK)
- Confluent Control Center
- Splunk

## Kafka Monitor

- Check doc [here](https://github.com/linkedin/kafka-monitor/tree/0.10.2.1)
- git clone https://github.com/linkedin/kafka-monitor.git -b 0.10.2.1
- cd kafka-monitor
- /gradlew jar
- Change: Zookeeper & Kafka config in config/kafka-monitor.properties
    - zookeeper.connect as "zookeeper1:2181,zookeeper2:2181,zookeeper3:2181/kafka"
    - bootstrap.servers as "kafka1:9092,kafka2:9092,kafka3:9092"
- start using "./bin/kafka-monitor-start.sh config/kafka-monitor.properties"

    - <img width="1660" alt="Screen Shot 2021-11-03 at 1 20 02 PM" src="https://user-images.githubusercontent.com/23295769/140025366-b6b63244-db04-4f56-b00d-76f9606206cb.png">

    - <img width="1626" alt="Screen Shot 2021-11-03 at 1 20 14 PM" src="https://user-images.githubusercontent.com/23295769/140025386-8eac06d9-b45d-4666-a038-e376b569b0da.png">


## Prometheus Setup

- JMX Exporer, do this one every instance of Kafka Broker
    - Check out https://github.com/prometheus/jmx_exporter
    - mkdir prometheus
    - cd prometheus
    - wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.3.1/jmx_prometheus_javaagent-0.3.1.jar
    - wget https://raw.githubusercontent.com/prometheus/jmx_exporter/master/example_configs/kafka-0-8-2.yml
    - add below kafka variablein kafka-run-class.sh
        -   KAFKA_OPTS=-javaagent:/home/ubuntu/prometheus/jmx_prometheus_javaagent-0.3.1.jar=8080:/home/ubuntu/prometheus/kafka-0-8-2.yml
    - curl localhost:8080, this command will give you various JMX data exposed by kafka

- Login to Kafka Admin Weeb Tool Instance
    -  curl kafka1:8080 or curl kafka2:8080, this command will give you various JMX data exposed by kafka
    -  Setup Promotheus 
        - wget https://github.com/prometheus/prometheus/releases/download/v2.3.2/prometheus-2.3.2.linux-amd64.tar.gz
        - tar -xzf prometheus-*.tar.gz
        - mv prometheus-2.3.2.linux-amd64 prometheus
        - goto prometheus folder and update prometheus.yml as per repo. Make sure you change
        - start prometheus using "./prometheus" this will start application on 9090 port
        - <img width="1677" alt="Screen Shot 2021-11-03 at 2 53 03 PM" src="https://user-images.githubusercontent.com/23295769/140035762-90cd7a51-936c-4b16-b05d-339c0b748c2c.png">
