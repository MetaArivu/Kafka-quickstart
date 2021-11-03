# Kafka Tools

## Install Monitor Tools
- [Zoo Navigator](https://hub.docker.com/r/elkozmon/zoonavigator)
- [Kafka Manager](https://github.com/yahoo/CMAK) OR [Here](https://hub.docker.com/r/hlebalbau/kafka-manager/)
- [Kafka Monitor](https://github.com/linkedin/kafka-monitor)

## Zoo Navigator
Steps for this are provided in [here](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/04-zookeeper-tools.md)

## Kafka Manager
- nano kafka-manager-docker-compose.yml, copy cotent from [here](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/kafka-manager-docker-compose.yml)
- docker-compose -f kafka-manager-docker-compose.yml up -d
- Access tool using public-ip:9000

- <img width="1673" alt="Screen Shot 2021-11-01 at 11 10 00 PM" src="https://user-images.githubusercontent.com/23295769/139715592-d26b5ac7-1a7b-4dfc-a377-911ec707a3a4.png">
- <img width="1645" alt="Screen Shot 2021-11-01 at 11 10 18 PM" src="https://user-images.githubusercontent.com/23295769/139715611-932c4ed0-70d3-4852-abd9-fe230aae2375.png">

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



## Kafka Topic UI
- nano kafka-topics-ui-docker-compose.yml, copy content into this from [here](https://github.com/MetaArivu/Kafka-quickstart/blob/main/08-cluster-setup/kafka-topics-ui-docker-compose.yml). Make sure you have updated the IP address with your Admin Server public address..
- docker-compose -f  kafka-topics-ui-docker-compose.yml up -d
- Access tool using public-ip:8000





